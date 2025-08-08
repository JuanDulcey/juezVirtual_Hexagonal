package com.dulceyson.juezVirtual.domain.ports.in;

import com.dulceyson.juezVirtual.domain.models.SubmissionRequest;
import com.dulceyson.juezVirtual.domain.models.SubmissionResult;

public interface SubmissionUseCase {
    SubmissionResult submitSolution(SubmissionRequest request);
}
