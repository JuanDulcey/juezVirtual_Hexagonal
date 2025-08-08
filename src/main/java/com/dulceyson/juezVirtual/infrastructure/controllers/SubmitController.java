package com.dulceyson.juezVirtual.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class SubmitController {

    @GetMapping("/submit")
    public String showSubmitPage(@RequestParam(required = false) String problemId, Model model) {
        model.addAttribute("problemId", problemId);
        return "submit";
    }

}
