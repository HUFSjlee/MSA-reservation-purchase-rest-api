package com.service.orderservice.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "mock.resilience-probe", name = "enabled", havingValue = "true")
public class ResilienceProbeService {

    private final MockStockClient mockStockClient;
    private final AtomicLong userSequence = new AtomicLong(1_000_000L);

    @Value("${mock.resilience-probe.duration-seconds:60}")
    private long durationSeconds;

    @Value("${mock.resilience-probe.interval-ms:1000}")
    private long intervalMs;

    private Instant startedAt;
    private long totalCalls;
    private long successCalls;
    private long failedCalls;
    private boolean finished;

    @Scheduled(fixedRateString = "${mock.resilience-probe.interval-ms:1000}")
    public void runProbe() {
        if (finished) {
            return;
        }

        if (startedAt == null) {
            startedAt = Instant.now();
            log.info("Resilience probe started. duration={}s, interval={}ms",
                    durationSeconds, intervalMs);
        }

        if (Duration.between(startedAt, Instant.now()).getSeconds() >= durationSeconds) {
            log.info("Resilience probe finished. totalCalls={}, successCalls={}, failedCalls={}",
                    totalCalls, successCalls, failedCalls);
            finished = true;
            return;
        }

        totalCalls++;
        try {
            MockStockClient.StockPaymentEntryResult result =
                    mockStockClient.enterPayment(userSequence.getAndIncrement(), 2L);
            successCalls++;
            log.info("Probe call success. paymentSessionId={}, remainingStock={}",
                    result.paymentSessionId(), result.remainingStock());
        } catch (Exception exception) {
            failedCalls++;
            log.warn("Probe call failed. reason={}", exception.getMessage());
        }
    }
}
