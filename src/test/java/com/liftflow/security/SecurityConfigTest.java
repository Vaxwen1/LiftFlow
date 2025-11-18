package com.liftflow.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SecurityConfigTest {

    private UserDetailsService userDetailsService;
    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        userDetailsService = mock(UserDetailsService.class);
        securityConfig = new SecurityConfig(userDetailsService);
    }

    @Test
    void passwordEncoder_isBCrypt() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void authenticationProvider_isDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                securityConfig.authenticationProvider(userDetailsService);

        assertNotNull(provider);
        assertTrue(provider instanceof DaoAuthenticationProvider);
    }
}
