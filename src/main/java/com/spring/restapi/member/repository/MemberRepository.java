package com.spring.restapi.member.repository;

import com.spring.restapi.member.doamin.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
