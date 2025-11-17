package com.liftflow.controller;

import com.liftflow.model.Donation;
import com.liftflow.model.dto.DonationRequest;
import com.liftflow.repository.DonationJarRepository;
import com.liftflow.repository.UserRepository;
import com.liftflow.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.math.BigDecimal;
import java.util.Arrays;

@Controller
@RequestMapping("/donation")   // Jar is still the main resource
public class DonationController {

    private final DonationService donationService;
    private final UserRepository userRepo;
    private final DonationJarRepository jarRepo;

    public DonationController(DonationService donationService,
                              UserRepository userRepo,
                              DonationJarRepository jarRepo) {
        this.donationService = donationService;
        this.userRepo = userRepo;
        this.jarRepo = jarRepo;
    }


    /**
     * Show donation form (HTML)
     * URL: GET /donation-jars/{jarId}/donations/new
     */
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

    /**
     * Handle form submit
     * URL: POST /donation-jars/{jarId}/donations
     *
     * - Guest (not logged in): userId stays null in DonationRequest.
     *   DonationService will use ANONYMOUS_USER_ID.
     * - Logged in: you can set request.setUserId(loggedInUserId) if you have it.
     */
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

    /** Показать форму Refund (вводим transactionId и amount вручную) */
    @GetMapping("/refund")
    public String refundForm(Model model) {
        model.addAttribute("transactionId", "");
        model.addAttribute("amount", "");
        return "Donation/refund_form"; // <-- путь к твоему файлу
    }

    /** Обработать Refund */
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
