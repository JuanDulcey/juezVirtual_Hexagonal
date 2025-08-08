package com.dulceyson.juezVirtual.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContact(Model model) {
        model.addAttribute("tilte", "Contacto");
        return "contact/contact";
    }
}
