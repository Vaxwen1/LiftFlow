package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HomeControllerTest {

    private UserService userService;
    private HomeController controller;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        controller = new HomeController(userService);
    }

    @Test
    void home_returnsHomeView() {
        assertEquals("home", controller.home());
    }

    @Test
    void registerForm_addsNewUserToModel() {
        Model model = new ExtendedModelMap();

        String view = controller.registerForm(model);

        assertEquals("register", view);
        assertTrue(model.containsAttribute("user"));
        assertTrue(model.getAttribute("user") instanceof User);
    }

    @Test
    void registerSubmit_withValidationErrors_returnsRegister() {
        User user = new User();
        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");
        result.reject("error");

        String view = controller.registerSubmit(user, result, model);

        assertEquals("register", view);
        verify(userService, never()).create(any());
    }

    @Test
    void registerSubmit_emailAlreadyExists_returnsRegisterWithError() {
        User existing = new User();
        existing.setEmail("test@mail.com");
        when(userService.findAll()).thenReturn(List.of(existing));

        User user = new User();
        user.setEmail("TEST@mail.com");

        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");

        String view = controller.registerSubmit(user, result, model);

        assertEquals("register", view);
        assertTrue(model.containsAttribute("emailError"));
        verify(userService, never()).create(any());
    }

    @Test
    void registerSubmit_ok_setsDefaultsAndRedirects() {
        when(userService.findAll()).thenReturn(List.of());

        User user = new User();
        user.setEmail("new@mail.com");
        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");

        String view = controller.registerSubmit(user, result, model);

        assertEquals("redirect:/login?registered=true", view);
        assertEquals('D', user.getRole());
        assertEquals('A', user.getStatus());
        verify(userService).create(user);
    }
}
