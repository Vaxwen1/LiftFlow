package com.liftflow.controller;

import com.liftflow.model.DonationJar;
import com.liftflow.model.User;
import com.liftflow.service.DonationJarService;
import com.liftflow.service.UserService;
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
    private UserService userService;


    // LIST + MY LIST
    @GetMapping
    public String listJars(Model model) {

        List<DonationJar> jars = service.getAll();

        model.addAttribute("jars", jars);

        //user id
        Integer currentUserId = 1;

        List<DonationJar> myJars = jars.stream()
                .filter(j -> j.getCreatedBy() != null &&
                        j.getCreatedBy().getUserId().equals(currentUserId))
                .toList();

        model.addAttribute("myJars", myJars);

        return "jars/list";
    }


    // CREATE FORM
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("jar", new DonationJar());
        model.addAttribute("isEdit", false);  // флаг для шаблона
        return "jars/create";
    }

    @PostMapping
    public String createJar(@Valid @ModelAttribute("jar") DonationJar jar,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "jars/create";
        }

        service.create(jar);
        return "redirect:/jars";
    }


    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {

        DonationJar jar = service.getById(id)
                .orElseThrow(() -> new RuntimeException("Jar not found"));

        model.addAttribute("jar", jar);
        model.addAttribute("isEdit", true);   // флаг для шаблона

        return "jars/edit";
    }


    @PostMapping("/{id}")
    public String updateJar(@PathVariable Integer id,
                            @Valid @ModelAttribute("jar") DonationJar jar,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "jars/create"; // errors -> back to form
        }

        service.update(id, jar);

        return "redirect:/jars";
    }




    @GetMapping("/api")
    @ResponseBody
    public List<DonationJar> getAllApi() {
        return service.getAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<DonationJar> getByIdApi(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public DonationJar createApi(@Valid @RequestBody DonationJar jar) {
        return service.create(jar);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<DonationJar> updateApi(@PathVariable Integer id, @Valid @RequestBody DonationJar jar) {
        try {
            return ResponseEntity.ok(service.update(id, jar));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

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

}
