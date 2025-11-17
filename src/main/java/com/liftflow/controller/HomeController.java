package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    // --------------------------
    // Landing / Dashboard
    // --------------------------
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User current = (User) session.getAttribute("currentUser");
        if (current == null) {
            return "home"; // public landing page
        }
        model.addAttribute("username", current.getUsername());
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User current = (User) session.getAttribute("currentUser");
        if (current == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", current.getUsername());
        return "dashboard";
    }

    // --------------------------
    // Login
    // --------------------------
    @GetMapping("/login")
    public String loginPage(HttpSession session, Model model) {
        User current = (User) session.getAttribute("currentUser");
        if (current != null) {
            model.addAttribute("username", current.getUsername());
            return "dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String email,
                          @RequestParam("password") String password,
                          HttpSession session,
                          Model model) {

        // Very simple login: find user by email and compare plain password
        User found = userService.findAll().stream()
                .filter(u -> u.getEmail() != null && u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        if (found == null || found.getPassword() == null || !found.getPassword().equals(password)) {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }

        session.setAttribute("currentUser", found);
        return "redirect:/dashboard";
    }

    // --------------------------
    // Logout
    // --------------------------
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // --------------------------
    // Registration
    // --------------------------
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        boolean emailExists = userService.findAll().stream()
                .anyMatch(u -> u.getEmail() != null
                        && user.getEmail() != null
                        && u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (emailExists) {
            model.addAttribute("user", user);
            model.addAttribute("emailError", "Email already registered");
            return "register";
        }

        if (user.getRole() == '\0') user.setRole('D');  // Donor
        if (user.getStatus() == '\0') user.setStatus('A'); // Active

        userService.create(user);
        return "redirect:/login?registered=true";
    }
}
