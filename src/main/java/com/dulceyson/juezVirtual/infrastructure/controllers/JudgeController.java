package com.dulceyson.juezVirtual.infrastructure.controllers;

import com.dulceyson.juezVirtual.domain.models.SubmissionRequest;
import com.dulceyson.juezVirtual.domain.ports.in.SubmissionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/judge")
public class JudgeController {

    private final SubmissionUseCase submissionUseCase;

    public JudgeController(SubmissionUseCase submissionUseCase) {
        this.submissionUseCase = submissionUseCase;
    }

    @GetMapping("/submit")
    public String showForm() {
        return "submit";
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseEntity<?> submitSolution(@RequestBody SubmissionRequest request) {
        try {
            return ResponseEntity.ok(submissionUseCase.submitSolution(request));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
