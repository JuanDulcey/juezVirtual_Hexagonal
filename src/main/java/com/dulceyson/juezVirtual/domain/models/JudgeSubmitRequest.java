package com.dulceyson.juezVirtual.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JudgeSubmitRequest {

    @JsonProperty("problem_url")
    private String problem_url;
    @JsonProperty("source_code_path")
    private String source_code_path;
}
