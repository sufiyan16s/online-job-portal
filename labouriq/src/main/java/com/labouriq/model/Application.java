package com.labouriq.model;

import java.time.LocalDateTime;

public class Application {

    private int id;
    private int jobId;
    private int seekerId;
    private String status;
    private LocalDateTime appliedAt;

    // ===================== ADDED =====================
    // Default constructor (required by DAO)
    public Application() {
    }

    // ===================== EXISTING =====================
    public Application(int jobId, int seekerId) {
        this.jobId = jobId;
        this.seekerId = seekerId;
    }

    public Application(int id, int jobId, int seekerId, String status, LocalDateTime appliedAt) {
        this.id = id;
        this.jobId = jobId;
        this.seekerId = seekerId;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    // ===================== GETTERS =====================
    public int getId() {
        return id;
    }

    public int getJobId() {
        return jobId;
    }

    public int getSeekerId() {
        return seekerId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    // ===================== ADDED SETTERS =====================
    public void setId(int id) {
        this.id = id;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setSeekerId(int seekerId) {
        this.seekerId = seekerId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
