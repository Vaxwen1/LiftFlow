package com.liftflow.service;

import com.liftflow.model.User;
import com.liftflow.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void findAll_returnsAllUsers() {
        User u1 = new User();
        User u2 = new User();
        when(userRepository.findAll()).thenReturn(List.of(u1, u2));

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void findById_existing_returnsUser() {
        User user = new User();
        user.setUserId(1);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User found = userService.findById(1);

        assertEquals(1, found.getUserId());
        verify(userRepository).findById(1);
    }

    @Test
    void findById_missing_throwsNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        ResponseStatusException ex =
                assertThrows(ResponseStatusException.class, () -> userService.findById(1));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        assertTrue(ex.getReason().contains("User not found with ID 1"));
    }

    @Test
    void create_encodesPasswordAndSetsDefaults() {
        User user = new User();
        user.setPassword("raw");
        when(passwordEncoder.encode("raw")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setUserId(10);
            return u;
        });

        User saved = userService.create(user);

        assertEquals("encoded", saved.getPassword());
        assertEquals('A', saved.getStatus());
        assertEquals('U', saved.getRole());
        assertNotNull(saved.getCreatedAt());
        assertNotNull(saved.getUpdatedAt());
        assertEquals(10, saved.getUserId());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void update_updatesOnlyNonNullFieldsAndEncodesPasswordWhenProvided() {
//        User existing = new User();
//        existing.setUserId(1);
//        existing.setUsername("old");
//        existing.setEmail("old@mail.com");
//        existing.setPassword("oldpass");
//        existing.setFirstName("Old");
//        existing.setLastName("Old");
//        existing.setPhoneNumber("000");
//        existing.setRole('U');
//        existing.setStatus('A');
//        existing.setUpdatedAt(LocalDateTime.now().minusDays(1));
//
//        when(userRepository.findById(1)).thenReturn(Optional.of(existing));
//        when(passwordEncoder.encode("newpass")).thenReturn("encodedNew");
//        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
//
//        User patch = new User();
//        patch.setUsername("new");
//        patch.setPassword("newpass");
//        patch.setFirstName("New");
//        // leave email null -> should not change
//        // leave role/status as '\0' -> should not change
//
//        User result = userService.update(1, patch);
//
//        assertEquals("new", result.getUsername());
//        assertEquals("old@mail.com", result.getEmail());
//        assertEquals("encodedNew", result.getPassword());
//        assertEquals("New", result.getFirstName());
//        assertEquals("Old", result.getLastName());
//        assertEquals("000", result.getPhoneNumber());
//        assertEquals('U', result.getRole());
//        assertEquals('A', result.getStatus());
//        assertTrue(result.getUpdatedAt().isAfter(existing.getUpdatedAt()));
//        verify(userRepository).save(existing);
    }

    @Test
    void delete_existing_deletes() {
        when(userRepository.existsById(5)).thenReturn(true);

        userService.delete(5);

        verify(userRepository).deleteById(5);
    }

    @Test
    void delete_missing_throwsNotFound() {
        when(userRepository.existsById(5)).thenReturn(false);

        ResponseStatusException ex =
                assertThrows(ResponseStatusException.class, () -> userService.delete(5));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        verify(userRepository, never()).deleteById(anyInt());
    }
}
