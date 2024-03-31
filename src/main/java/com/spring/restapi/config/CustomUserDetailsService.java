package com.spring.restapi.config;

import com.spring.restapi.core.exception.NotFoundDataException;
import com.spring.restapi.member.doamin.Member;
import com.spring.restapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findById(Long.parseLong(username))
                .map(this::createUserDetails)
                .orElseThrow(() -> new NotFoundDataException("Member not found"));
    }

    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getId().toString())
                .password(member.getPassword())
                .build();
    }
}
