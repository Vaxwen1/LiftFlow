package com.liftflow.controller;

import com.liftflow.service.FundraiserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fundraiser")
public class FundraiserController {

    private final FundraiserService fundraiserService;

    public FundraiserController(FundraiserService fundraiserService) {
        this.fundraiserService = fundraiserService;
    }

    @GetMapping("/upgrade")
    public String showUpgradePage() {
        return "fundraiser_upgrade";
    }

    @PostMapping("/upgrade")
    public String upgradeFundraiser(
            @RequestParam("passportNumber") String passportNumber,
            @RequestParam("issuingCountry") String issuingCountry,
            @RequestParam("image") MultipartFile image,
            RedirectAttributes redirectAttributes) {

        try {
            fundraiserService.submitKyc(passportNumber, issuingCountry, image);
            redirectAttributes.addFlashAttribute("success", "You are now a verified fundraiser!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/fundraiser_upgrade";
    }
}
