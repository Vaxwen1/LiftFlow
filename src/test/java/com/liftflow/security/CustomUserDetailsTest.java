package com.liftflow.security;

import com.liftflow.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    @Test
    void wrapsUserAndExposesFieldsCorrectly() {
        User user = new User();
        user.setUserId(Integer.valueOf(42));
        user.setEmail("test@mail.com");
        user.setPassword("secret");

        CustomUserDetails cud = new CustomUserDetails(user);

        assertEquals(42, cud.getId());
        assertEquals(user, cud.getUser());
        assertEquals("test@mail.com", cud.getEmail());
        assertEquals("test@mail.com", cud.getUsername());
        assertEquals("secret", cud.getPassword());

        assertNotNull(cud.getAuthorities());
        assertTrue(cud.getAuthorities().isEmpty());

        assertTrue(cud.isAccountNonExpired());
        assertTrue(cud.isAccountNonLocked());
        assertTrue(cud.isCredentialsNonExpired());
        assertTrue(cud.isEnabled());
    }
}
