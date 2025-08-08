package com.dulceyson.juezVirtual.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class SubmissionRecord {

    @JsonProperty("judgeId")
    private Long id;

    private String userId;
    private String problemId;
    private String language;
    private String status;
    private int cpuTime;
    private int memory;
    private int codeSize;

    private Instant submissionDate;

    private String token;

    // Mapeo de timestamp en milisegundos a Instant
    @JsonProperty("submissionDate")
    public void setSubmissionDate(long timestamp) {
        this.submissionDate = Instant.ofEpochMilli(timestamp);
    }

    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProblemId() {
        return problemId;
    }

    @JsonProperty("problemId")
    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    @JsonProperty("cpuTime")
    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getMemory() {
        return memory;
    }

    @JsonProperty("memory")
    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getCodeSize() {
        return codeSize;
    }

    @JsonProperty("codeSize")
    public void setCodeSize(int codeSize) {
        this.codeSize = codeSize;
    }

    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }
}
