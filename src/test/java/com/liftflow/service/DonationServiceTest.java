package com.liftflow.service;

import com.liftflow.model.Donation;
import com.liftflow.model.DonationJar;
import com.liftflow.model.Transaction;
import com.liftflow.model.User;
import com.liftflow.model.dto.DonationRequest;
import com.liftflow.repository.DonationJarRepository;
import com.liftflow.repository.DonationRepository;
import com.liftflow.repository.TransactionRepository;
import com.liftflow.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for DonationService.
 */
class DonationServiceTest {

    private DonationRepository donationRepo;
    private DonationJarRepository jarRepo;
    private UserRepository userRepo;
    private TransactionRepository txRepo;
    private DonationService service;

    @BeforeEach
    void setUp() {
        donationRepo = mock(DonationRepository.class);
        jarRepo = mock(DonationJarRepository.class);
        userRepo = mock(UserRepository.class);
        txRepo = mock(TransactionRepository.class);
        service = new DonationService(donationRepo, jarRepo, userRepo, txRepo);
    }

    @Test
    void addDonation_success_createsDonationAndTransaction_andIncreasesJar() {
        User user = new User();
        user.setUserId(1);
        DonationJar jar = new DonationJar();
        jar.setJarId(10);
        jar.setCurrentAmount(new BigDecimal("100.00"));

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(jarRepo.findById(10)).thenReturn(Optional.of(jar));

        Donation savedDonation = new Donation();
        savedDonation.setDonationId(777);
        when(donationRepo.save(any(Donation.class))).thenReturn(savedDonation);

        DonationRequest req = new DonationRequest();
        req.setUserId(1);
        req.setJarId(10);
        req.setAmount(new BigDecimal("50.00"));
        req.setPaymentMethod("Card");

        Donation result = service.addDonation(req);

        assertNotNull(result);
        assertEquals(777, result.getDonationId());
        assertEquals(new BigDecimal("150.00"), jar.getCurrentAmount());
        verify(jarRepo).save(jar);

        ArgumentCaptor<Transaction> txCap = ArgumentCaptor.forClass(Transaction.class);
        verify(txRepo, atLeastOnce()).save(txCap.capture());
        boolean hasCompletedTx = txCap.getAllValues().stream()
                .anyMatch(t -> t.getDonation() == savedDonation
                        && t.getTransactionAmount().compareTo(new BigDecimal("50.00")) == 0
                        && "Completed".equalsIgnoreCase(t.getTransactionStatus()));
        assertTrue(hasCompletedTx);
    }

    @Test
    void refundDonation_success_createsRefundTx_andDecreasesJar() {
        User user = new User();
        user.setUserId(1);
        DonationJar jar = new DonationJar();
        jar.setJarId(10);
        jar.setCurrentAmount(new BigDecimal("2000.00"));

        Donation donation = new Donation();
        donation.setDonationId(500);
        donation.setUser(user);
        donation.setJar(jar);

        Transaction original = new Transaction();
        original.setTransactionId(100);
        original.setDonation(donation);
        original.setTransactionAmount(new BigDecimal("2000.00"));
        original.setTransactionDate(LocalDateTime.now());

        when(txRepo.findById(100)).thenReturn(Optional.of(original));

        Transaction refund = service.refundDonation(100, new BigDecimal("1000.00"));

        assertEquals(new BigDecimal("1000.00"), jar.getCurrentAmount());
        verify(jarRepo).save(jar);
        verify(txRepo, atLeast(1)).save(refund);
    }

    @Test
    void addDonation_userNotFound_throws() {
        DonationRequest req = new DonationRequest();
        req.setUserId(999); req.setJarId(10);
        req.setAmount(new BigDecimal("10.00"));
        when(userRepo.findById(999)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.addDonation(req));
    }

    @Test
    void addDonation_jarNotFound_throws() {
        DonationRequest req = new DonationRequest();
        req.setUserId(1); req.setJarId(999);
        req.setAmount(new BigDecimal("10.00"));
        when(userRepo.findById(1)).thenReturn(Optional.of(new User()));
        when(jarRepo.findById(999)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.addDonation(req));
    }

    @Test
    void refundDonation_exceedsOriginal_throws() {
        Transaction original = new Transaction();
        original.setTransactionId(1);
        original.setTransactionAmount(new BigDecimal("50"));
        when(txRepo.findById(1)).thenReturn(Optional.of(original));
        assertThrows(IllegalArgumentException.class,
                () -> service.refundDonation(1, new BigDecimal("60")));
    }

}
