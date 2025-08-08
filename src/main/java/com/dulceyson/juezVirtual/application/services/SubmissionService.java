package com.dulceyson.juezVirtual.application.services;

import com.dulceyson.juezVirtual.domain.models.SubmissionRequest;
import com.dulceyson.juezVirtual.domain.models.SubmissionResult;
import com.dulceyson.juezVirtual.domain.ports.in.SubmissionUseCase;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders; // <- aquí está el correcto
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class SubmissionService implements SubmissionUseCase {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String SUBMISSION_URL = "https://judgeapi.u-aizu.ac.jp/submissions";
    private static final String RECENT_URL = "https://judgeapi.u-aizu.ac.jp/submission_records/recent";
    private static final String COOKIE_HEADER = "JSESSIONID=8825BDB21DC910601BE52E138CAE0C1F";

    @Override
    public SubmissionResult submitSolution(SubmissionRequest request) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("problemId", request.problemId);
            payload.put("language", request.language);
            payload.put("sourceCode", request.sourceCode);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Cookie", COOKIE_HEADER);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
            ResponseEntity<Map> submissionResponse = restTemplate.postForEntity(SUBMISSION_URL, entity, Map.class);

            if (!submissionResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error en envío");
            }

            String token = (String) submissionResponse.getBody().get("token");

            HttpHeaders recentHeaders = new HttpHeaders();
            recentHeaders.add("Cookie", COOKIE_HEADER);
            HttpEntity<Void> recentEntity = new HttpEntity<>(recentHeaders);

            ResponseEntity<List> recentResponse = restTemplate.exchange(
                    RECENT_URL,
                    HttpMethod.GET,
                    recentEntity,
                    List.class
            );

            if (!recentResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error consultando recientes");
            }

            List<Map<String, Object>> recentSubmissions = recentResponse.getBody();
            Optional<Map<String, Object>> found = recentSubmissions.stream()
                    .filter(s -> token.equals(s.get("token")))
                    .findFirst();

            if (found.isEmpty()) {
                throw new RuntimeException("Token no encontrado en recientes");
            }

            Map<String, Object> submissionInfo = found.get();

            SubmissionResult result = new SubmissionResult();
            result.token = token;
            result.problemId = (String) submissionInfo.get("problemId");
            result.language = (String) submissionInfo.get("language");
            result.status = String.valueOf(submissionInfo.get("status"));
            result.accuracy = (String) submissionInfo.get("accuracy");
            result.problemTitle = (String) submissionInfo.get("problemTitle");

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Error interno: " + e.getMessage());
        }
    }
}
