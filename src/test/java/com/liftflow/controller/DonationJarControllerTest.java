package com.liftflow.controller;

import com.liftflow.model.DonationJar;
import com.liftflow.model.User;
import com.liftflow.service.DonationJarService;
import com.liftflow.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonationJarControllerTest {

    private DonationJarService jarService;
    private UserRepository userRepository;
    private DonationJarController controller;

    @BeforeEach
    void setUp() {
        jarService = mock(DonationJarService.class);
        userRepository = mock(UserRepository.class);
        controller = new DonationJarController(jarService, userRepository);

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
    void listJars_populatesJarsAndMyJars() {
//        User user = new User();
//        user.setUserId(1);
//        when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));
//
//        DonationJar jar1 = new DonationJar();
//        jar1.setCreatedBy(user);
//        DonationJar jar2 = new DonationJar();
//        jar2.setCreatedBy(new User());
//
//        when(jarService.getAll()).thenReturn(List.of(jar1, jar2));
//
//        Model model = new ExtendedModelMap();
//        String view = controller.listJars(model);
//
//        assertEquals("jars/list", view);
//        assertEquals(2, ((List<?>) model.getAttribute("jars")).size());
//        assertEquals(1, ((List<?>) model.getAttribute("myJars")).size());
    }

    @Test
    void createJar_withErrors_returnsCreateView() {
        // DonationJar jar = new DonationJar();
        // BindingResult result = new BeanPropertyBindingResult(jar, "jar");
        // result.reject("err");
        // String view = controller.createJar(jar, result);

        // assertEquals("jars/create", view);
        // verify(jarService, never()).create(any());
    }

    @Test
    void createJar_ok_setsCreatedByAndRedirects() {
        // User user = new User();
        // user.setUserId(1);
        // when(userRepository.findByEmail("user@mail.com")).thenReturn(Optional.of(user));

        // DonationJar jar = new DonationJar();
        // BindingResult result = new BeanPropertyBindingResult(jar, "jar");

        // String view = controller.createJar(jar, result);

        // assertEquals("redirect:/jars", view);
        // assertEquals(user, jar.getCreatedBy());
        // verify(jarService).create(jar);
    }

    @Test
    void deleteJar_success_setsSuccessFlashAndRedirects() {
        RedirectAttributes attrs = new RedirectAttributesModelMap();

        String view = controller.deleteJar(5, attrs);

        assertEquals("redirect:/jars", view);
        assertTrue(attrs.getFlashAttributes().containsKey("successMessage"));
        verify(jarService).delete(5);
    }

    @Test
    void deleteJar_failure_setsErrorFlashAndRedirects() {
        doThrow(new IllegalStateException("Cannot delete"))
                .when(jarService).delete(5);

        RedirectAttributes attrs = new RedirectAttributesModelMap();

        String view = controller.deleteJar(5, attrs);

        assertEquals("redirect:/jars", view);
        assertTrue(attrs.getFlashAttributes().containsKey("errorMessage"));
    }
}
