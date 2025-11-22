package com.liftflow.controller;

import com.liftflow.service.FundraiserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FundraiserControllerTest {

    private FundraiserService fundraiserService;
    private FundraiserController controller;

    @BeforeEach
    void setUp() {
        fundraiserService = mock(FundraiserService.class);
        controller = new FundraiserController(fundraiserService);
    }

    @Test
    void showUpgradePage_returnsUpgradeView() {
        String view = controller.showUpgradePage();
        assertEquals("fundraiser_upgrade", view);
    }

    @Test
    void upgradeFundraiser_success_setsSuccessFlashAndRedirects() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", "passport.png", "image/png", "dummy".getBytes()
        );
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        String view = controller.upgradeFundraiser(
                "P1234567",
                "IE",
                file,
                redirectAttributes
        );

        assertEquals("redirect:/fundraiser_upgrade", view);
        assertTrue(redirectAttributes.getFlashAttributes().containsKey("success"));
        assertEquals("You are now a verified fundraiser!",
                redirectAttributes.getFlashAttributes().get("success"));

        verify(fundraiserService).submitKyc("P1234567", "IE", file);
    }

    @Test
    void upgradeFundraiser_exception_setsErrorFlashAndRedirects() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "image", "passport.png", "image/png", "dummy".getBytes()
        );
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        doThrow(new RuntimeException("Something went wrong"))
                .when(fundraiserService)
                .submitKyc(anyString(), anyString(), any());

        String view = controller.upgradeFundraiser(
                "P1234567",
                "IE",
                file,
                redirectAttributes
        );

        assertEquals("redirect:/fundraiser_upgrade", view);
        assertTrue(redirectAttributes.getFlashAttributes().containsKey("error"));
        assertEquals("Something went wrong",
                redirectAttributes.getFlashAttributes().get("error"));
    }
}
