package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /** List all users */
    @GetMapping
    public String list(Model model) {
        List<User> users = service.findAll();
        model.addAttribute("users", users);
        return "users"; // templates/users.html
    }

    /** Show create form */
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("mode", "create");
        return "user_form"; // templates/user_form.html
    }

    /** Handle create */
    @PostMapping
    public String create(@ModelAttribute("user") User formUser) {
        service.create(formUser);
        return "redirect:/users";
    }

    /** Show edit form */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        User user = service.findById(id); // 404 if not found
        model.addAttribute("user", user);
        model.addAttribute("mode", "edit");
        return "user_form";
    }

    /** Handle update (POST from edit form) */
    @PostMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("user") User formUser) {
        service.update(id, formUser);
        return "redirect:/users";
    }

    /** Handle delete (POST from list row) */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return "redirect:/users";
    }
}
