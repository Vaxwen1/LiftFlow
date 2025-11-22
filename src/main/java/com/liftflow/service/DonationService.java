package com.liftflow.service;

import com.liftflow.model.*;
import com.liftflow.model.dto.DonationRequest;
import com.liftflow.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DonationService {

    private static final int ANONYMOUS_USER_ID = 1;

    private final DonationRepository donationRepo;
    private final DonationJarRepository jarRepo;
    private final UserRepository userRepo;
    private final TransactionRepository txRepo;

    public DonationService(DonationRepository donationRepo,
                           DonationJarRepository jarRepo,
                           UserRepository userRepo,
                           TransactionRepository txRepo) {
        this.donationRepo = donationRepo;
        this.jarRepo = jarRepo;
        this.userRepo = userRepo;
        this.txRepo = txRepo;
    }

    /** Add Donation: create Donation + Transaction + increase jar.currentAmount */
    @Transactional
    public Donation addDonation(DonationRequest req) {

        // ---- basic validation ----
        if (req.getAmount() == null || req.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (req.getJarId() == null) {
            throw new IllegalArgumentException("Jar id is required");
        }

        // ---- 1) Resolve user (guest vs logged-in) ----
        User user;
        if (req.getUserId() == null) {  // guest / non-logged
            user = userRepo.findById(ANONYMOUS_USER_ID)
                    .orElseThrow(() -> new IllegalStateException(
                            "Anonymous user not found. Please create user with id=" + ANONYMOUS_USER_ID));
        } else {                        // logged-in
            user = userRepo.findById(req.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "User not found with id " + req.getUserId()));
        }

        // ---- 2) Resolve jar ----
        DonationJar jar = jarRepo.findById(req.getJarId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Jar not found with id " + req.getJarId()));

        // ---- 3) Create & save Donation ----
        Donation donation = new Donation();
        donation.setDonationAmount(req.getAmount());
        donation.setDonationDate(LocalDateTime.now());
        donation.setUser(user);
        donation.setJar(jar);

        donation = donationRepo.save(donation);  // <-- now donation has ID

        // ---- 4) Create & save Transaction row ----
        Transaction tx = new Transaction();
        tx.setTransactionAmount(req.getAmount());
        tx.setTransactionDate(LocalDateTime.now());
        tx.setTransactionStatus("Completed");                          // or "Pending" if you need
        tx.setPaymentMethod(
                req.getPaymentMethod() == null ? "Unknown" : req.getPaymentMethod()
        );
        tx.setDonation(donation);  // FK to Donation

        txRepo.save(tx);           // <-- this writes into dbo.[Transaction]

        // ---- 5) Update jar currentAmount ----
        BigDecimal current = jar.getCurrentAmount();
        if (current == null) {
            current = BigDecimal.ZERO;
        }
        jar.setCurrentAmount(current.add(req.getAmount()));
        jar.setUpdatedAt(LocalDateTime.now());

        jarRepo.save(jar);         // <-- update dbo.DonationJar

        return donation;
    }

    /** Refund Donation by original transaction id */
    @Transactional
    public Transaction refundDonation(Integer originalTxId, BigDecimal refundAmount) {
        if (refundAmount == null || refundAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Refund amount must be > 0");

        Transaction original = txRepo.findById(originalTxId)
                .orElseThrow(() -> new IllegalArgumentException("Original transaction not found"));

        Donation donation = original.getDonation();
        DonationJar jar = donation.getJar();

        if (refundAmount.compareTo(original.getTransactionAmount()) > 0)
            throw new IllegalArgumentException("Refund cannot exceed original amount");

        // ✅ 1) Создаём новую refund-транзакцию
        Transaction refund = new Transaction();
        refund.setTransactionAmount(refundAmount.negate()); // отрицательная сумма (для наглядности)
        refund.setTransactionDate(LocalDateTime.now());
        refund.setDonation(donation);
        refund.setTransactionStatus("Completed");
        refund.setPaymentMethod("Refund");
        refund.setRefundAmount(refundAmount);
        refund.setRefundDate(LocalDateTime.now());
        refund.setRefundStatus("Completed");
        refund.setOriginalTransaction(original);
        txRepo.save(refund);

        // ✅ 2) Обновляем оригинал
        original.setRefundAmount(refundAmount);
        original.setRefundDate(LocalDateTime.now());
        original.setRefundStatus("Completed");
        txRepo.save(original);

        // ✅ 3) Уменьшаем баланс банки (DonationJar)
        BigDecimal current = jar.getCurrentAmount();
        if (current == null) current = BigDecimal.ZERO;

        BigDecimal newAmount = current.subtract(refundAmount);
        if (newAmount.compareTo(BigDecimal.ZERO) < 0) newAmount = BigDecimal.ZERO; // защита от отрицательных значений

        jar.setCurrentAmount(newAmount);
        jar.setUpdatedAt(LocalDateTime.now());
        jarRepo.save(jar);

        // ✅ 4) Вернём refund-транзакцию
        return refund;
    }


}
