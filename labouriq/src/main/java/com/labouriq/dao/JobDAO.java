package com.labouriq.dao;

import com.labouriq.db.DBConnection;
import com.labouriq.model.Job;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public Job create(Job j) throws SQLException {
        String sql = "INSERT INTO jobs (employer_id, title, description, location, salary, status, created_at) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, j.getEmployerId());
            ps.setString(2, j.getTitle());
            ps.setString(3, j.getDescription());
            ps.setString(4, j.getLocation());
            ps.setString(5, j.getSalary());
            ps.setString(6, j.getStatus() == null ? "OPEN" : j.getStatus());
            ps.setString(7, j.getCreatedAt() == null ? LocalDateTime.now().toString() : j.getCreatedAt().toString());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) j.setId(rs.getInt(1));
        }
        return j;
    }

    public List<Job> findByEmployerId(int employerId) throws SQLException {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE employer_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Job> findAll() throws SQLException {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM jobs ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    private Job mapRow(ResultSet rs) throws SQLException {
        Job j = new Job();
        j.setId(rs.getInt("id"));
        j.setEmployerId(rs.getInt("employer_id"));
        j.setTitle(rs.getString("title"));
        j.setDescription(rs.getString("description"));
        j.setLocation(rs.getString("location"));
        j.setSalary(rs.getString("salary"));
        j.setStatus(rs.getString("status"));
        String created = rs.getString("created_at");
        if (created != null) j.setCreatedAt(LocalDateTime.parse(created));
        return j;
    }
}
