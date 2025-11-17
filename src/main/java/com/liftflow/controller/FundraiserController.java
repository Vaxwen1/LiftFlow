package com.liftflow.controller;

import com.liftflow.model.User;
import com.liftflow.model.UserKyc;
import com.liftflow.repository.UserKycRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/fundraiser")
public class FundraiserController {

    private final UserKycRepository kycRepo;

    public FundraiserController(UserKycRepository kycRepo) {
        this.kycRepo = kycRepo;
    }

    // ---------- GET: show upgrade form ----------
    @GetMapping("/upgrade")
    public String upgradeForm(HttpSession session, Model model) {
        User cur = (User) session.getAttribute("currentUser");
        if (cur == null) {
            return "redirect:/login";
        }

        // Just show empty form
        return "fundraiser_upgrade";
    }

    // ---------- POST: handle submitted form ----------
    @PostMapping("/upgrade")
    public String handleUpgrade(@RequestParam("passportNumber") String passportNumber,
                                @RequestParam("issuingCountry") String issuingCountry,
                                @RequestParam(value = "passportImage", required = false) MultipartFile image,
                                HttpSession session,
                                Model model) {
        User cur = (User) session.getAttribute("currentUser");
        if (cur == null) {
            return "redirect:/login";
        }

        if (passportNumber == null || passportNumber.trim().isEmpty()
                || issuingCountry == null || issuingCountry.trim().isEmpty()) {
            model.addAttribute("formError", "Passport number and issuing country are required.");
            return "fundraiser_upgrade";
        }

        UserKyc kyc = new UserKyc();
        kyc.setUser(cur);
        kyc.setPassportNumber(passportNumber.trim());
        kyc.setIssuingCountry(issuingCountry.trim());
        kyc.setSubmittedAt(LocalDateTime.now());
        kyc.setStatus("PENDING");

        // --- handle optional image upload ---
        if (image != null && !image.isEmpty()) {
            try {
                // Use stable directory in user home, e.g. C:\Users\polta\liftflow_uploads
                String baseUploadDir = System.getProperty("user.home") + File.separator + "liftflow_uploads";
                Path uploadPath = Paths.get(baseUploadDir).toAbsolutePath().normalize();

                // Ensure directory exists
                Files.createDirectories(uploadPath);

                String ext = StringUtils.getFilenameExtension(image.getOriginalFilename());
                String storedName = "passport_" + UUID.randomUUID() + (ext != null ? "." + ext : "");
                Path target = uploadPath.resolve(storedName);

                image.transferTo(target.toFile());

                // URL path (served by WebConfig as /uploads/**)
                kyc.setImagePath("/uploads/" + storedName);
            } catch (Exception e) {
                model.addAttribute("formError", "Could not save image: " + e.getMessage());
                return "fundraiser_upgrade";
            }
        }

        kycRepo.save(kyc);

        // Optionally mark user as fundraiser in memory
        if (cur.getRole() == '\0' || cur.getRole() == 'D') {
            cur.setRole('F');
        }

        // After successful KYC submit → show status page
        return "redirect:/fundraiser/status";
    }

    // ---------- GET: show KYC status (with image) ----------
    @GetMapping("/status")
    public String kycStatus(HttpSession session, Model model) {
        User cur = (User) session.getAttribute("currentUser");
        if (cur == null) {
            return "redirect:/login";
        }

        // Find the first KYC record for this user in memory
        UserKyc kyc = kycRepo.findAll().stream()
                .filter(k -> k.getUser() != null
                        && k.getUser().getUserId() != null
                        && k.getUser().getUserId().equals(cur.getUserId()))
                .findFirst()
                .orElse(null);

        model.addAttribute("kyc", kyc);
        model.addAttribute("user", cur);

        return "fundraiser_status";
    }
}
