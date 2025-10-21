package com.liftflow.controller;

import com.liftflow.model.dto.DonationRequest;
import com.liftflow.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/donations")
public class DonationController {

    private final DonationService service;
    public DonationController(DonationService service) { this.service = service; }

    // форма добавления доната
    @GetMapping("/new")
    public String newDonationForm(Model model) {
        model.addAttribute("donation", new DonationRequest());
        return "Donation/donation_form"; // => templates/Donation/donation_form.html
    }

    // создание доната из формы
    @PostMapping
    public String addDonation(@ModelAttribute("donation") com.liftflow.model.dto.DonationRequest request,
                              org.springframework.ui.Model model) {
        try {
            service.addDonation(request);
            model.addAttribute("success", "Donation successfully added!");
            return "Donation/donation_form";
        } catch (org.springframework.dao.DataAccessException ex) {
            // покажем корневую ошибку от драйвера SQL Server
            String root = (ex.getMostSpecificCause() != null) ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
            model.addAttribute("error", root);
            return "Donation/donation_form";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "Donation/donation_form";
        } catch (Exception ex) {
            model.addAttribute("error", "Unexpected: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
            return "Donation/donation_form";
        }
    }

}
