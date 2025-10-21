package com.liftflow.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Donations", schema = "dbo")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Integer donationId;

    @Column(name = "donation_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal donationAmount;

    @Column(name = "donation_date", nullable = false)
    private LocalDateTime donationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jar_id", nullable = false)
    private DonationJar jar;

    public Donation() {}

    public Integer getDonationId() { return donationId; }
    public void setDonationId(Integer donationId) { this.donationId = donationId; }

    public BigDecimal getDonationAmount() { return donationAmount; }
    public void setDonationAmount(BigDecimal donationAmount) { this.donationAmount = donationAmount; }

    public LocalDateTime getDonationDate() { return donationDate; }
    public void setDonationDate(LocalDateTime donationDate) { this.donationDate = donationDate; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public DonationJar getJar() { return jar; }
    public void setJar(DonationJar jar) { this.jar = jar; }
}