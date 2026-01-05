package com.labouriq.model;

public class Job {

    private int id;
    private int employerId;
    private String title;
    private String location;
    private String salary;
    private String description;
    private String status;

    // ✅ REQUIRED EMPTY CONSTRUCTOR
    public Job() {}

    // ================= GETTERS =================
    public int getId() {
        return id;
    }

    public int getEmployerId() {
        return employerId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    // ================= SETTERS =================
    public void setId(int id) {
        this.id = id;
    }

    // 🔥 THIS WAS MISSING (CAUSE OF ERROR)
    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
