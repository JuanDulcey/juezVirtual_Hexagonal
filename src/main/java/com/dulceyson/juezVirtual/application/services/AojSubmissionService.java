package com.dulceyson.juezVirtual.application.services;

import com.dulceyson.juezVirtual.domain.models.SubmissionRecord;
import com.dulceyson.juezVirtual.domain.ports.out.AojSubmissionClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AojSubmissionService {

    private final AojSubmissionClient aojSubmissionClient;

    public AojSubmissionService(AojSubmissionClient aojSubmissionClient) {
        this.aojSubmissionClient = aojSubmissionClient;
    }

    public List<SubmissionRecord> getAllSubmissions() {
        return aojSubmissionClient.fetchAllSubmissions();
    }

    public SubmissionRecord waitForFinalResult(String token) {
        int maxRetries = 30;  // máximo 30 intentos (~60 segundos)
        int intervalMillis = 2000;

        for (int i = 0; i < maxRetries; i++) {
            SubmissionRecord record = aojSubmissionClient.fetchSubmissionByToken(token);
            if (isFinalStatus(record.getStatus())) {
                return record;
            }
            try {
                Thread.sleep(intervalMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Polling interrupted", e);
            }
        }

        throw new RuntimeException("Timeout: No final result after polling");
    }

    private boolean isFinalStatus(String status) {
        return List.of(
                "AC", "WA", "TLE", "MLE", "RE", "CE", "OLE", "QLE", "PE", "IE"
        ).contains(status);
    }

}
