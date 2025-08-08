package com.dulceyson.juezVirtual.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingController {
    @GetMapping("/ranking")
    public String showRankingPage(Model model) {
        model.addAttribute("title", "Ranking");
        return "ranking/ranking";
    }

}
