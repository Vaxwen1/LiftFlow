package com.liftflow.controller;

import com.liftflow.model.Donation;
import com.liftflow.model.User;
import com.liftflow.model.dto.DonationRequest;
import com.liftflow.repository.DonationJarRepository;
import com.liftflow.repository.DonationRepository;
import com.liftflow.repository.UserRepository;
import com.liftflow.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/donation")   // Jar is still the main resource
public class DonationController {

    private final DonationService donationService;
    private final UserRepository userRepo;
    private final DonationJarRepository jarRepo;
    private final DonationRepository donationRepo;

    public DonationController(DonationService donationService,
                              UserRepository userRepo,
                              DonationJarRepository jarRepo, DonationRepository donationRepo) {
        this.donationService = donationService;
        this.userRepo = userRepo;
        this.jarRepo = jarRepo;
        this.donationRepo = donationRepo;
    }



    // show HTML form
    @GetMapping("/{jarId}/donations/new")
    public String showDonationForm(@PathVariable Integer jarId, Model model) {
        try {
            DonationRequest form = new DonationRequest();
            form.setJarId(jarId);               // pre-select this jar

            // *** IMPORTANT: attribute name must be "donation" ***
            model.addAttribute("donation", form);

            // same data your old controller used
            model.addAttribute("users", userRepo.findAll());
            model.addAttribute("jar", jarRepo.findById(jarId).orElseThrow());
            model.addAttribute("paymentMethods",
                    Arrays.asList("Card", "PayPal", "Crypto", "Bank Transfer"));
        } catch (DataAccessException ex) {
            model.addAttribute(
                    "error",
                    "Database error while loading users/jars: " +
                            (ex.getMostSpecificCause() != null
                                    ? ex.getMostSpecificCause().getMessage()
                                    : ex.getMessage())
            );
        }

        // path: src/main/resources/templates/Donation/donation_form.html
        return "Donation/donation_form";
    }



    @PostMapping("/{jarId}/donations")
    public String createDonation(@PathVariable Integer jarId,
                                 @ModelAttribute("donation") DonationRequest request,
                                 RedirectAttributes redirectAttributes) {
        request.setJarId(jarId);

        try {
            donationService.addDonation(request);

            redirectAttributes.addFlashAttribute("success", "Donation saved successfully.");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            // validation or logical problems
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        } catch (DataAccessException ex) {
            // DB-level problems
            String root = (ex.getMostSpecificCause() != null)
                    ? ex.getMostSpecificCause().getMessage()
                    : ex.getMessage();
            redirectAttributes.addFlashAttribute("error", "Database error: " + root);
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Unexpected error: " + ex.getClass().getSimpleName() + " - " + ex.getMessage()
            );
        }

        return "redirect:/donation/" + jarId + "/donations/new";
    }



    @GetMapping("/refund")
    public String refundForm(Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {

        // 1. Находим юзера по email (или username — см. ниже)
        User user = userRepo.findByEmailWithDonations(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Берём все донаты этого юзера из DonationRepository
        List<Donation> donations = donationRepo.findByUser(user);

        System.out.println("donations size = " + donations.size());
        for (Donation d : donations) {
            System.out.println("ID=" + d.getDonationId()
                    + " amount=" + d.getDonationAmount()
                    + " date=" + d.getDonationDate());
        }

        // 3. Кладём в модель
        model.addAttribute("donations", donations);
        model.addAttribute("transactionId", "");
        model.addAttribute("amount", "");

        return "Donation/refund_form";
    }



    @PostMapping("/refund")
    public String refund(
            @RequestParam("transactionId") String txIdStr,
            @RequestParam("amount") String amountStr,
            Model model) {

        try {
            Integer txId = Integer.valueOf(txIdStr.trim());
            BigDecimal amount = new BigDecimal(amountStr.replace(',', '.').trim());

            donationService.refundDonation(txId, amount);

            model.addAttribute("success", "Refund completed for transaction #" + txId);
            model.addAttribute("transactionId", "");
            model.addAttribute("amount", "");
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("transactionId", txIdStr);
            model.addAttribute("amount", amountStr);
        } catch (DataAccessException ex) {
            String root = (ex.getMostSpecificCause() != null) ? ex.getMostSpecificCause().getMessage() : ex.getMessage();
            model.addAttribute("error", root);
            model.addAttribute("transactionId", txIdStr);
            model.addAttribute("amount", amountStr);
        } catch (Exception ex) {
            model.addAttribute("error", "Unexpected: " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
            model.addAttribute("transactionId", txIdStr);
            model.addAttribute("amount", amountStr);
        }
        return "Donation/refund_form"; // <-- имя вью совпадает с файлом
    }
}
