package com.spring.restapi.member.repository;

import com.spring.restapi.member.doamin.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Integer> countByEmail(String email);

    Optional<Boolean> existsByEmail(String email);

}
