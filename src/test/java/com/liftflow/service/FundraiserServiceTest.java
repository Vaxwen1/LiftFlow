package com.liftflow.service;

import com.liftflow.model.User;
import com.liftflow.model.UserKyc;
import com.liftflow.repository.UserKycRepository;
import com.liftflow.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundraiserServiceTest {

    private UserKycRepository kycRepo;
    private UserRepository userRepo;
    private FundraiserService service;

    @BeforeEach
    void setUp() {
        kycRepo = mock(UserKycRepository.class);
        userRepo = mock(UserRepository.class);
        service = new FundraiserService(kycRepo, userRepo);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(
                new UsernamePasswordAuthenticationToken("user@mail.com", "pwd"));
        SecurityContextHolder.setContext(context);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void submitKyc_withImage_savesKycAndUpdatesRole() throws Exception {
        User user = new User();
        user.setUserId(1);
        user.setRole('U');

        when(userRepo.findByEmail("user@mail.com")).thenReturn(Optional.of(user));
        when(kycRepo.save(any(UserKyc.class)))
                .thenAnswer(inv -> inv.getArgument(0));
        when(userRepo.save(any(User.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        MockMultipartFile file = new MockMultipartFile(
                "image", "passport.png", "image/png", "dummy".getBytes());

        service.submitKyc("12345", "IE", file);

        verify(kycRepo).save(any(UserKyc.class));
        verify(userRepo).save(user);
        assertEquals('F', user.getRole());
    }

    @Test
    void submitKyc_noUser_throwsRuntime() {
        when(userRepo.findByEmail("user@mail.com")).thenReturn(Optional.empty());

        MockMultipartFile file = new MockMultipartFile(
                "image", "passport.png", "image/png", "dummy".getBytes());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.submitKyc("12345", "IE", file));

        assertTrue(ex.getMessage().contains("User not found"));
        verify(kycRepo, never()).save(any());
    }

    @Test
    void submitKyc_emptyImage_doesNotFail() throws Exception {
        User user = new User();
        user.setUserId(1);
        when(userRepo.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        MockMultipartFile emptyFile = new MockMultipartFile(
                "image", "", "application/octet-stream", new byte[0]);

        service.submitKyc("12345", "IE", emptyFile);

        verify(kycRepo).save(any(UserKyc.class));
        verify(userRepo).save(user);
    }
}
