package com.dulceyson.juezVirtual.infrastructure.controllers;

import com.dulceyson.juezVirtual.application.services.AojSubmissionService;
import com.dulceyson.juezVirtual.domain.models.SubmissionRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aoj")
public class AojSubmissionController {

    private final AojSubmissionService aojSubmissionService;

    public AojSubmissionController(AojSubmissionService aojSubmissionService) {
        this.aojSubmissionService = aojSubmissionService;
    }

    @GetMapping("/submissions")
    public List<SubmissionRecord> getSubmissions() {
        return aojSubmissionService.getAllSubmissions();
    }
}
