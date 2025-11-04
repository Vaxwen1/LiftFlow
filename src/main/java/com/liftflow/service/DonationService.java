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
        if (req.getAmount() == null || req.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be > 0");

        User user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        DonationJar jar = jarRepo.findById(req.getJarId())
                .orElseThrow(() -> new IllegalArgumentException("Jar not found"));

        // 1) Donation
        Donation d = new Donation();
        d.setDonationAmount(req.getAmount());
        d.setDonationDate(LocalDateTime.now());
        d.setUser(user);
        d.setJar(jar);
        d = donationRepo.save(d);

        // 2) Transaction (считаем успешной — Completed; можно сделать Pending)
        Transaction tx = new Transaction();
        tx.setTransactionAmount(req.getAmount());
        tx.setTransactionDate(LocalDateTime.now());
        tx.setDonation(d);
        tx.setTransactionStatus("Completed"); // или "Pending" если ждёшь платёжку
        tx.setPaymentMethod(req.getPaymentMethod() == null ? "Unknown" : req.getPaymentMethod());
        txRepo.save(tx);

        // 3) Update jar amount
        jar.setCurrentAmount(jar.getCurrentAmount().add(req.getAmount()));
        jar.setUpdatedAt(LocalDateTime.now());
        jarRepo.save(jar);

        return d;
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
