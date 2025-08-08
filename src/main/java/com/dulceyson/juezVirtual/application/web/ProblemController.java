package com.dulceyson.juezVirtual.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProblemController {

    @GetMapping("/problems")
    public String showDifficultyPage() {
        return "problems/dificulties"; // Esta vista ya la tienes
    }

    @GetMapping("/problems/easy")
    public String showEasyProblems() {
        return "problems/easy"; // Crea easy.html
    }

    @GetMapping("/problems/medium")
    public String showMediumProblems() {
        return "problems/medium"; // Crea medium.html
    }

    @GetMapping("/problems/hard")
    public String showHardProblems() {
        return "problems/hard"; // Crea hard.html
    }
}


