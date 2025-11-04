package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // --- List all users ---
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    // --- Show create form ---
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("mode", "create");
        return "user_form";
    }

    // --- Handle new user creation ---
    @PostMapping
    public String createUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "create");
            return "user_form";
        }

        if (user.getRole() == '\0') user.setRole('D');
        if (user.getStatus() == '\0') user.setStatus('A');

        userService.create(user);
        return "redirect:/users";
    }

    // --- Show edit form ---
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("mode", "edit");
        return "user_form";
    }

    // --- Handle update ---
    @PostMapping("/{id}")
    public String updateUser(
            @PathVariable("id") Integer id,
            @Valid @ModelAttribute("user") User updatedUser,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("mode", "edit");
            return "user_form";
        }

        userService.update(id, updatedUser);
        return "redirect:/users";
    }

    // --- Delete user ---
    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
