package com.dulceyson.juezVirtual.infrastructure.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index"; // Esto buscará src/main/resources/templates/index.html
    }
}