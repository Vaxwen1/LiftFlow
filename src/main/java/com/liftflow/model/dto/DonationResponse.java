package com.liftflow.model.dto;

import com.liftflow.model.Donation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DonationResponse {

    private Integer donationId;
    private BigDecimal amount;
    private LocalDateTime donationDate;
    private Integer userId;
    private Integer jarId;

    public DonationResponse(Donation donation) {
        this.donationId = donation.getDonationId();
        this.amount = donation.getDonationAmount();
        this.donationDate = donation.getDonationDate();
        this.userId = (donation.getUser() != null) ? donation.getUser().getUserId() : null;
        this.jarId = (donation.getJar() != null) ? donation.getJar().getJarId() : null;
    }

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDateTime donationDate) {
        this.donationDate = donationDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJarId() {
        return jarId;
    }

    public void setJarId(Integer jarId) {
        this.jarId = jarId;
    }
}
