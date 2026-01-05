package com.labouriq.dao;

import com.labouriq.model.Job;
import com.labouriq.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public void create(Job job) throws Exception {
        String sql = """
            INSERT INTO jobs (employer_id, title, location, salary, description)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, job.getEmployerId());
            ps.setString(2, job.getTitle());
            ps.setString(3, job.getLocation());
            ps.setString(4, job.getSalary());
            ps.setString(5, job.getDescription());
            ps.executeUpdate();
        }
    }

    public List<Job> findByEmployer(int employerId) throws Exception {
        List<Job> list = new ArrayList<>();

        String sql = "SELECT * FROM jobs WHERE employer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setEmployerId(employerId);
                job.setTitle(rs.getString("title"));
                job.setLocation(rs.getString("location"));
                job.setSalary(rs.getString("salary"));
                job.setDescription(rs.getString("description"));
                job.setStatus(rs.getString("status"));
                list.add(job);
            }
        }
        return list;
    }

    public List<Job> findAllOpenJobs() throws Exception {
        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT * FROM jobs WHERE status = 'OPEN'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setTitle(rs.getString("title"));
                job.setLocation(rs.getString("location"));
                job.setSalary(rs.getString("salary"));
                job.setDescription(rs.getString("description"));
                job.setStatus(rs.getString("status"));
                jobs.add(job);
            }
        }
        return jobs;
    }

    public int countActiveJobs(int employerId) throws Exception {
        String sql = "SELECT COUNT(*) FROM jobs WHERE employer_id=? AND status='OPEN'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employerId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public int countApplicants(int employerId) throws Exception {
        String sql = """
        SELECT COUNT(*) 
        FROM applications a
        JOIN jobs j ON a.job_id = j.id
        WHERE j.employer_id=?
    """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employerId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

}
