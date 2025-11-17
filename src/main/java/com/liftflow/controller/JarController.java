package com.liftflow.controller;

import com.liftflow.model.DonationJar;
import com.liftflow.model.User;
import com.liftflow.repository.DonationJarRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/jars")
public class JarController {

    private final DonationJarRepository jarRepo;

    public JarController(DonationJarRepository jarRepo) {
        this.jarRepo = jarRepo;
    }

    @GetMapping
    public String list(HttpSession session, Model model) {
        User cur = (User) session.getAttribute("currentUser");
        if (cur == null) return "redirect:/login";
        model.addAttribute("jars", jarRepo.findByCreatedBy(cur));
        return "jars";
    }

    @GetMapping("/new")
    public String createForm(HttpSession session, Model model) {
        User cur = (User) session.getAttribute("currentUser");
        if (cur == null) return "redirect:/login";

        DonationJar jar = new DonationJar();
        jar.setStartDate(LocalDate.now()); // prefill today for the form if you like

        model.addAttribute("jar", jar);
        return "jar_form";
    }

    @PostMapping
    public String create(@ModelAttribute("jar") DonationJar jar, HttpSession session) {
        User cur = (User) session.getAttribute("currentUser");
        if (cur == null) return "redirect:/login";

        jar.setCreatedBy(cur);
        // currentAmount, createdAt, updatedAt, startDate are handled by @PrePersist in entity
        jarRepo.save(jar);
        return "redirect:/jars";
    }
}
