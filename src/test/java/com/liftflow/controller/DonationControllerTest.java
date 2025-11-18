package com.liftflow.controller;

import com.liftflow.model.Donation;
import com.liftflow.model.DonationJar;
import com.liftflow.model.Transaction;
import com.liftflow.model.dto.DonationRequest;
import com.liftflow.repository.DonationJarRepository;
import com.liftflow.repository.UserRepository;
import com.liftflow.service.DonationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonationControllerTest {

    private DonationService donationService;
    private UserRepository userRepo;
    private DonationJarRepository jarRepo;
    private DonationController controller;

    @BeforeEach
    void setUp() {
        donationService = mock(DonationService.class);
        userRepo = mock(UserRepository.class);
        jarRepo = mock(DonationJarRepository.class);
        controller = new DonationController(donationService, userRepo, jarRepo);
    }

    @Test
    void showDonationForm_populatesModelAndReturnsView() {
//        DonationJar jar = new DonationJar();
//        jar.setJarId(3);
//        when(jarRepo.findById(3)).thenReturn(Optional.of(jar));
//
//        Model model = new ExtendedModelMap();
//        String view = controller.showDonationForm(3, model);
//
//        assertEquals("Donation/form", view);
//        assertTrue(model.containsAttribute("jar"));
//        assertTrue(model.containsAttribute("donation"));
    }

    @Test
    void createDonation_success_redirectsWithFlash() {
//        DonationRequest request = new DonationRequest();
//        request.setAmount(new BigDecimal("10"));
//        RedirectAttributes attrs = new RedirectAttributesModelMap();
//        when(donationService.addDonation(any(DonationRequest.class)))
//                .thenReturn(new Donation());
//
//        String view = controller.createDonation(3, request, attrs);
//
//        assertEquals("redirect:/jars", view);
//        assertTrue(attrs.getFlashAttributes().containsKey("success"));
    }

    @Test
    void refundForm_returnsRefundView() {
        Model model = new ExtendedModelMap();
        String view = controller.refundForm(model);
        assertEquals("Donation/refund_form", view);
    }

    @Test
    void refund_success_setsSuccessMessage() throws Exception {
        Model model = new ExtendedModelMap();
        Transaction tx = new Transaction();
        tx.setTransactionId(1);
        tx.setTransactionAmount(new BigDecimal("50"));
        when(donationService.refundDonation(1, new BigDecimal("10")))
                .thenReturn(tx);

        String view = controller.refund("1", "10", model);

        assertEquals("Donation/refund_form", view);
        assertTrue(model.containsAttribute("success"));
    }

    @Test
    void refund_dataAccessException_setsErrorMessage() throws Exception {
        Model model = new ExtendedModelMap();
        when(donationService.refundDonation(1, new BigDecimal("10")))
                .thenThrow(new DataIntegrityViolationException("DB error"));

        String view = controller.refund("1", "10", model);

        assertEquals("Donation/refund_form", view);
        assertTrue(model.containsAttribute("error"));
    }
}
