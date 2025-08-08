package com.dulceyson.juezVirtual.infrastructure.adapters;

import com.dulceyson.juezVirtual.domain.models.SubmissionRecord;
import com.dulceyson.juezVirtual.domain.ports.out.AojSubmissionClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class AojSubmissionClientImp implements AojSubmissionClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<SubmissionRecord> fetchAllSubmissions() {
        String url = "https://judgeapi.u-aizu.ac.jp/submission_records/recent";
        SubmissionRecord[] records = restTemplate.getForObject(url, SubmissionRecord[].class);
        return Arrays.asList(records);
    }

    @Override
    public SubmissionRecord fetchSubmissionByToken(String token) {
        String url = "https://judgeapi.u-aizu.ac.jp/submission_records/" + token;
        return restTemplate.getForObject(url, SubmissionRecord.class);
    }
}
