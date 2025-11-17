package com.liftflow.model.dto;

import java.math.BigDecimal;

public class DonationRequest {
    private Integer userId;
    private Integer jarId;
    private BigDecimal amount;
    private String paymentMethod; // "Card", "PayPal", ...

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getJarId() { return jarId; }
    public void setJarId(Integer jarId) { this.jarId = jarId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}
