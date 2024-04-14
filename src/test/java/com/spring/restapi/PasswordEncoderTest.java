package com.spring.restapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PasswordEncoderTest {

    @Test
    void encode() {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("sha256", new MessageDigestPasswordEncoder("SHA-256"));

        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("sha256", encoders);

        String password = "1234";
        String encodePassword = passwordEncoder.encode(password);

        System.out.println(delegatingPasswordEncoder.encode(password));
        System.out.println(encodePassword);


        assert(passwordEncoder.matches(password, encodePassword));
        assert(encodePassword).contains("{bcrypt}");
    }
}
