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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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





    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        DonationJar jar = jarService.getById(id)
                .orElseThrow(() -> new RuntimeException("Jar not found"));

        model.addAttribute("jar", jar);
        return "jars/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateJar(@PathVariable Integer id,
                            @Valid @ModelAttribute("jar") DonationJar jar,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "jars/edit";
        }

        jarService.update(id, jar);
        return "redirect:/jars";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteJar(@PathVariable Integer id) {
//        jarService.delete(id);
//        return "redirect:/jars";
//    }

    @GetMapping("/delete/{id}")
    public String deleteJar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            jarService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Jar deleted successfully.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/jars";
    }



}
