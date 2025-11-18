package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;
    private UserController controller;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        controller = new UserController(userService);
    }

    @Test
    void listUsers_populatesModelAndReturnsView() {
        when(userService.findAll()).thenReturn(List.of(new User(), new User()));
        Model model = new ExtendedModelMap();

        String view = controller.listUsers(model);

        assertEquals("users", view);
        assertTrue(model.containsAttribute("users"));
        List<?> list = (List<?>) model.getAttribute("users");
        assertEquals(2, list.size());
        verify(userService).findAll();
    }

    @Test
    void showCreateForm_setsUserAndModeCreate() {
        Model model = new ExtendedModelMap();

        String view = controller.showCreateForm(model);

        assertEquals("user_form", view);
        assertTrue(model.containsAttribute("user"));
        assertEquals("create", model.getAttribute("mode"));
    }

    @Test
    void createUser_withErrors_returnsForm() {
        User user = new User();
        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");
        result.reject("error");

        String view = controller.createUser(user, result, model);

        assertEquals("user_form", view);
        assertEquals("create", model.getAttribute("mode"));
        verify(userService, never()).create(any());
    }

    @Test
    void createUser_ok_setsDefaultsAndRedirects() {
        User user = new User();
        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");

        String view = controller.createUser(user, result, model);

        assertEquals("redirect:/users", view);
        assertEquals('D', user.getRole());
        assertEquals('A', user.getStatus());
        verify(userService).create(user);
    }

    @Test
    void showEditForm_loadsUserAndSetsModeEdit() {
        User user = new User();
        user.setUserId(Integer.valueOf(1));
        when(userService.findById(Integer.valueOf(1))).thenReturn(user);

        Model model = new ExtendedModelMap();
        String view = controller.showEditForm(Integer.valueOf(1), model);

        assertEquals("user_form", view);
        assertEquals(user, model.getAttribute("user"));
        assertEquals("edit", model.getAttribute("mode"));
    }

    @Test
    void updateUser_withErrors_returnsForm() {
        User user = new User();
        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");
        result.reject("err");

        String view = controller.updateUser(Integer.valueOf(1), user, result, model);

        assertEquals("user_form", view);
        assertEquals("edit", model.getAttribute("mode"));
        verify(userService, never()).update(Integer.valueOf(ArgumentMatchers.anyInt()), any());
    }

    @Test
    void updateUser_ok_redirects() {
        User user = new User();
        Model model = new ExtendedModelMap();
        BindingResult result = new BeanPropertyBindingResult(user, "user");

        String view = controller.updateUser(Integer.valueOf(1), user, result, model);

        assertEquals("redirect:/users", view);
        verify(userService).update(Integer.valueOf(1), user);
    }

    @Test
    void deleteUser_callsServiceAndRedirects() {
        String view = controller.deleteUser(Integer.valueOf(3));

        assertEquals("redirect:/users", view);
        verify(userService).delete(Integer.valueOf(3));
    }
}
