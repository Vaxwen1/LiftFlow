package com.liftflow.security;

import com.liftflow.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LoggedUserAdvice {

    @ModelAttribute
    public void addLoggedUser(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()
                || auth.getPrincipal().equals("anonymousUser")) {
            return;
        }

        CustomUserDetails cud = (CustomUserDetails) auth.getPrincipal();
        User user = cud.getUser();

        model.addAttribute("loggedUser", user);
    }
}
