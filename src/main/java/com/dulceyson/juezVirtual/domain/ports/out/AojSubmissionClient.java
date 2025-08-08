package com.dulceyson.juezVirtual.domain.ports.out;

import com.dulceyson.juezVirtual.domain.models.SubmissionRecord;

import java.util.List;

public interface AojSubmissionClient {
    List<SubmissionRecord> fetchAllSubmissions();
    SubmissionRecord fetchSubmissionByToken(String token);
}
