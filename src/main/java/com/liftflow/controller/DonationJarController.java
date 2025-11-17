package com.liftflow.controller;

import com.liftflow.model.DonationJar;
import com.liftflow.model.User;
import com.liftflow.security.CustomUserDetails;
import com.liftflow.service.DonationJarService;
import com.liftflow.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/jars")
public class DonationJarController {

    private final DonationJarService jarService;
    private final UserRepository userRepository;

    public DonationJarController(DonationJarService jarService, UserRepository userRepository) {
        this.jarService = jarService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listJars(Model model) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("jars", jarService.getAll());

        List<DonationJar> myJars = jarService.getAll().stream()
                        .filter(j -> j.getCreatedBy() != null &&
                                j.getCreatedBy().getUserId().equals(user.getUserId())).toList();
        model.addAttribute("myJars", myJars);
        return "jars/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("jar", new DonationJar());
        return "jars/create";
    }

    @PostMapping
    public String createJar(@Valid @ModelAttribute("jar") DonationJar jar,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "jars/create";
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        jar.setCreatedBy(user);

        jarService.create(jar);

        return "redirect:/jars";
    }

}
