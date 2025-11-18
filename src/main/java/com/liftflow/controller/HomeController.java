package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    // --------------------------
    // Home Page (Public)
    // --------------------------
    @GetMapping("/")
    public String home() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()
                && !(auth instanceof AnonymousAuthenticationToken)) {
            // пользователь залогинен -> отправляем его куда надо
            return "redirect:/posts"; // или /dashboard, как тебе нужно
        }

        // гость -> показываем обычный home с "Login / Get Started"
        return "home"; // имя твоего шаблона
    }

    // --------------------------
    // Dashboard (Requires Login)
    // Spring Security controls access
    // --------------------------
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // --------------------------
    // Login (Spring Security handles POST)
    // --------------------------
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // --------------------------
    // Registration Form
    // --------------------------
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // --------------------------
    // Registration Submit
    // --------------------------
    @PostMapping("/register")
    public String registerSubmit(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        // Check email uniqueness
        boolean emailExists = userService.findAll().stream()
                .anyMatch(u -> u.getEmail() != null
                        && user.getEmail() != null
                        && u.getEmail().equalsIgnoreCase(user.getEmail()));

        if (emailExists) {
            model.addAttribute("emailError", "Email already registered");
            return "register";
        }

        // Set default values if empty
        if (user.getRole() == '\0') user.setRole('D');   // Donor
        if (user.getStatus() == '\0') user.setStatus('A'); // Active

        // Save (with BCrypt encode inside service)
        userService.create(user);

        // Redirect with message
        return "redirect:/login?registered=true";
    }
}
