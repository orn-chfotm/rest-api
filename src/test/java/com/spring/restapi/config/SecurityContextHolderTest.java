package com.spring.restapi.config;

import org.junit.jupiter.api.*;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("SecurityContextHolderTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecurityContextHolderTest {

    private String username;

    private String password;

    private String ROLE;

    @BeforeAll
    public void setUp() {
        username = "default@gmail.com";
        password = "password";
        ROLE = "ROLE_USER";
    }

    @Test
    @Order(1)
    void setSecurityContextHolder() throws Exception {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new TestingAuthenticationToken(username, password, ROLE);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    @Test
    @Order(2)
    void getAuthentication() throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        assertThat(authentication.isAuthenticated(), is(true));
        assertThat(authentication.getName(), is(username));
        assertThat(authentication.getCredentials(), is(password));
        assertThat(authentication.getAuthorities(), containsInAnyOrder(
                hasProperty("authority", is(ROLE))));

    }
}
