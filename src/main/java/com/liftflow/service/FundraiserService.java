package com.liftflow.service;

import com.liftflow.model.User;
import com.liftflow.model.UserKyc;
import com.liftflow.repository.UserKycRepository;
import com.liftflow.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FundraiserService {

    private final UserKycRepository kycRepo;
    private final UserRepository userRepo;

    public FundraiserService(UserKycRepository kycRepo, UserRepository userRepo) {
        this.kycRepo = kycRepo;
        this.userRepo = userRepo;
    }

    public void submitKyc(String passportNumber, String issuingCountry, MultipartFile image) throws Exception {

        // Get logged in user
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Save image if uploaded
        String fileName = null;

        if (!image.isEmpty()) {
            fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path uploadPath = Paths.get("src/main/resources/static/kyc");

            // Create folder if it doesn't exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(image.getInputStream(), uploadPath.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        }

        // Create & save KYC record
        UserKyc kyc = new UserKyc();
        kyc.setUser(user);
        kyc.setPassportNumber(passportNumber);
        kyc.setIssuingCountry(issuingCountry);
        kyc.setImagePath(fileName);
        kyc.setSubmittedAt(LocalDateTime.now());
        kyc.setStatus("APPROVED"); // auto-approve

        kycRepo.save(kyc);

        // Update user role to Fundraiser
        user.setRole('F');
        userRepo.save(user);
    }
}
