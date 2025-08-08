package com.dulceyson.juezVirtual.application.web;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController {
    @RequestMapping("/auth/login")
    public String showLoginForm() {
        return "auth/login";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException() {
        return "redirect:/auth/login?error=true";
    }

}
