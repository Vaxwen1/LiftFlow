package com.liftflow.controller;

import com.liftflow.model.Donation;
import com.liftflow.model.dto.DonationRequest;
import com.liftflow.repository.DonationJarRepository;
import com.liftflow.repository.UserRepository;
import com.liftflow.service.DonationService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/donations")
public class DonationController {

    private final DonationService service;
    private final UserRepository userRepo;
    private final DonationJarRepository jarRepo;

    public DonationController(DonationService service, UserRepository userRepo, DonationJarRepository jarRepo) {
        this.service = service;
        this.userRepo = userRepo;
        this.jarRepo = jarRepo;
    }


    // ---- ADD DONATION ----
    @GetMapping("/new")
    public String newDonationForm(Model model) {
        model.addAttribute("donation", new DonationRequest());
        model.addAttribute("users", userRepo.findAll());
        model.addAttribute("jars", jarRepo.findAll());
        model.addAttribute("paymentMethods", Arrays.asList("Card","PayPal","Crypto","Bank Transfer"));
        return "Donation/donation_form";
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

            service.refundDonation(txId, amount);

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
