package com.reservationpurchase.reservationpurchase.module.member.infrastructure;

import com.reservationpurchase.reservationpurchase.module.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
