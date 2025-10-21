package com.liftflow.controller;

import com.liftflow.model.DonationJar;
import com.liftflow.service.DonationJarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/jars")
public class DonationJarController {

    @Autowired
    private DonationJarService service;

    // Thymeleaf: Список всех банок
    @GetMapping
    public String listJars(Model model) {
        List<DonationJar> jars = service.getAll();
        model.addAttribute("jars", jars);
        return "jars/list"; // Шаблон: src/main/resources/templates/jars/list.html
    }

    // Thymeleaf: Форма создания банки
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("jar", new DonationJar());
        return "jars/create"; // Шаблон: src/main/resources/templates/jars/create.html
    }

    // Thymeleaf: Обработка создания банки
    @PostMapping
    public String createJar(@Valid @ModelAttribute("jar") DonationJar jar, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "jars/create";
        }
        service.create(jar);
        return "redirect:/jars";
    }

    // Thymeleaf: Форма добавления пожертвования
    @GetMapping("/{id}/donate")
    public String showDonateForm(@PathVariable Integer id, Model model) {
        DonationJar jar = service.getById(id).orElseThrow(() -> new RuntimeException("Jar not found"));
        model.addAttribute("jar", jar);
        model.addAttribute("amount", new BigDecimal("0.00"));
        return "jars/donate"; // Шаблон: src/main/resources/templates/jars/donate.html
    }

    // Thymeleaf: Обработка пожертвования
    @PostMapping("/{id}/donate")
    public String addDonation(@PathVariable Integer id, @RequestParam BigDecimal amount) {
        service.addDonation(id, amount);
        return "redirect:/jars";
    }

    // REST API: Получить все банки
    @GetMapping("/api")
    @ResponseBody
    public List<DonationJar> getAllApi() {
        return service.getAll();
    }

    // REST API: Получить банку по ID
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<DonationJar> getByIdApi(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // REST API: Создать банку
    @PostMapping("/api")
    @ResponseBody
    public DonationJar createApi(@Valid @RequestBody DonationJar jar) {
        return service.create(jar);
    }

    // REST API: Обновить банку
    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<DonationJar> updateApi(@PathVariable Integer id, @Valid @RequestBody DonationJar jar) {
        try {
            return ResponseEntity.ok(service.update(id, jar));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // REST API: Удалить банку
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteApi(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // REST API: Добавить пожертвование
    @PostMapping("/api/{id}/donate")
    @ResponseBody
    public ResponseEntity<DonationJar> addDonationApi(@PathVariable Integer id, @RequestParam BigDecimal amount) {
        try {
            return ResponseEntity.ok(service.addDonation(id, amount));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}