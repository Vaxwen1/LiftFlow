package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final UserService userService;

    // Constructor injection (recommended for Spring Boot 3+)
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    // --------------------------
    // Home page
    // --------------------------
    @GetMapping("/")
    public String home() {
        return "home"; // maps to templates/home.html
    }

    // --------------------------
    // Login page
    // --------------------------
    @GetMapping("/login")
    public String login() {
        return "login"; // maps to templates/login.html
    }

    // --------------------------
    // Registration form (GET)
    // --------------------------
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User()); // empty user object for form binding
        return "register"; // maps to templates/register.html
    }

    // --------------------------
    // Handle registration (POST)
    // --------------------------
    @PostMapping("/register")
    public String registerSubmit(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model
    ) {
        // 1. Basic validation (required fields)
        if (result.hasErrors()) {
            return "register";
        }

        // 2. Check if email already exists
        boolean emailExists = userService.findAll()
                .stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (emailExists) {
            model.addAttribute("user", user);
            model.addAttribute("emailError", "Email already registered");
            return "register";
        }

        // 3. Assign sensible defaults if not set
        if (user.getRole() == '\0') user.setRole('D'); // D = Donor (default role)
        if (user.getStatus() == '\0') user.setStatus('A'); // A = Active

        // 4. Save new user
        userService.create(user);

        // 5. Redirect to login with success flag
        return "redirect:/login?registered=true";
    }
}
