package com.labouriq.model;

import java.time.LocalDateTime;

public class Job {
    private int id;
    private int employerId;
    private String title;
    private String description;
    private String location;
    private String salary;
    private String status; // OPEN / CLOSED
    private LocalDateTime createdAt;

    public Job() {
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    // convenience constructor
    public Job(int employerId, String title, String description, String location, String salary) {
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.salary = salary;
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    // getters / setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getEmployerId() { return employerId; }
    public void setEmployerId(int employerId) { this.employerId = employerId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
