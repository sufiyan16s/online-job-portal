package com.labouriq.model;

import java.time.LocalDateTime;

public class Application {
    private int id;
    private int jobId;
    private int seekerId;
    private String resumePath;
    private String coverLetter;
    private String status; // PENDING / ACCEPTED / REJECTED
    private LocalDateTime appliedAt;

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }
    public int getSeekerId() { return seekerId; }
    public void setSeekerId(int seekerId) { this.seekerId = seekerId; }
    public String getResumePath() { return resumePath; }
    public void setResumePath(String resumePath) { this.resumePath = resumePath; }
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}
