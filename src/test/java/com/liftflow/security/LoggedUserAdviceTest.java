package com.liftflow.security;

import com.liftflow.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoggedUserAdviceTest {

    private LoggedUserAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new LoggedUserAdvice();
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void addLoggedUser_whenAuthIsNull_doesNothing() {
        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(context);

        Model model = new ExtendedModelMap();

        advice.addLoggedUser(model);

        assertFalse(model.containsAttribute("loggedUser"));
    }

    @Test
    void addLoggedUser_whenAnonymousUser_doesNothing() {
        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getPrincipal()).thenReturn("anonymousUser");

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Model model = new ExtendedModelMap();

        advice.addLoggedUser(model);

        assertFalse(model.containsAttribute("loggedUser"));
    }

    @Test
    void addLoggedUser_whenAuthenticated_addsLoggedUserToModel() {
        User user = new User();
        user.setUserId(1);
        user.setEmail("logged@mail.com");

        CustomUserDetails cud = new CustomUserDetails(user);

        Authentication auth = mock(Authentication.class);
        when(auth.isAuthenticated()).thenReturn(true);
        when(auth.getPrincipal()).thenReturn(cud);

        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);

        Model model = new ExtendedModelMap();

        advice.addLoggedUser(model);

        assertTrue(model.containsAttribute("loggedUser"));
        assertEquals(user, model.getAttribute("loggedUser"));
    }
}
