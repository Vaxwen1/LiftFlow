package com.liftflow.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FundRaisers", schema = "dbo")
public class FundRaiser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_raiser_id")
    private Integer fundRaiserId;

    // связь 1:1 с User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "passport_number", length = 100)
    private String passportNumber;

    @Column(name = "passport_img", length = 255)
    private String passportImg;

    @Column(name = "passport_verified", nullable = false)
    private boolean passportVerified;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public FundRaiser() {}

    // ----- геттеры/сеттеры -----
    public Integer getFundRaiserId() { return fundRaiserId; }
    public void setFundRaiserId(Integer fundRaiserId) { this.fundRaiserId = fundRaiserId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    public String getPassportImg() { return passportImg; }
    public void setPassportImg(String passportImg) { this.passportImg = passportImg; }

    public boolean isPassportVerified() { return passportVerified; }
    public void setPassportVerified(boolean passportVerified) { this.passportVerified = passportVerified; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
