$ErrorActionPreference = "Stop"

param(
    [string]$GatewayBaseUrl = "http://localhost:8080",
    [int]$PaymentEntryCount = 10000,
    [int]$PaymentTryCount = 8000,
    [long]$ProductId = 1
)

function Invoke-JsonPost {
    param(
        [string]$Url,
        [object]$Body
    )

    $json = $Body | ConvertTo-Json -Depth 5
    Invoke-RestMethod -Method Post -Uri $Url -Body $json -ContentType "application/json"
}

$paymentEntryUrl = "$GatewayBaseUrl/orders/mock/payment-entry"
$paymentUrl = "$GatewayBaseUrl/orders/mock/payment"
$remainingUrl = "$GatewayBaseUrl/stocks/mock/$ProductId/remaining"

Write-Host "[1/3] Sending remaining stock request before load..."
Invoke-RestMethod -Method Get -Uri $remainingUrl | Out-Null

Write-Host "[2/3] Sending $PaymentEntryCount payment-entry requests..."
$sessions = New-Object System.Collections.Generic.List[string]

for ($userId = 1; $userId -le $PaymentEntryCount; $userId++) {
    try {
        $response = Invoke-JsonPost -Url $paymentEntryUrl -Body @{
            userId    = $userId
            productId = $ProductId
        }
        if ($response.result.paymentSessionId) {
            $sessions.Add($response.result.paymentSessionId)
        }
    } catch {
        # Keep going for bulk-load behavior.
    }
}

Write-Host "Payment-entry success count: $($sessions.Count)"

Write-Host "[3/3] Sending up to $PaymentTryCount payment requests..."
$payCount = [Math]::Min($PaymentTryCount, $sessions.Count)
$successCount = 0
$failCount = 0

for ($i = 0; $i -lt $payCount; $i++) {
    try {
        $response = Invoke-JsonPost -Url $paymentUrl -Body @{
            paymentSessionId = $sessions[$i]
        }
        if ($response.result.success -eq $true) {
            $successCount++
        } else {
            $failCount++
        }
    } catch {
        $failCount++
    }
}

Write-Host "Payment success count: $successCount"
Write-Host "Payment fail count: $failCount"

Write-Host "Sending remaining stock request after load..."
$remaining = Invoke-RestMethod -Method Get -Uri $remainingUrl
Write-Host "Remaining stock: $($remaining.result.remainingStock)"

