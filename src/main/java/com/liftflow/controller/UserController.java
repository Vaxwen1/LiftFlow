package com.liftflow.controller;


import com.liftflow.model.User;
import com.liftflow.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    // список
    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }

    // форма создания
    @GetMapping("/users/new")
    public String newUserForm(Model model) {
        User u = new User();
        u.setRole('F');
        u.setStatus('A');
        model.addAttribute("user", u);
        return "user_form";
    }

    // сохранение
    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        service.create(user); // createdAt/updatedAt выставятся в сервисе
        return "redirect:/";
    }
}