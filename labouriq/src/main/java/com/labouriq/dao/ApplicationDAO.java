package com.labouriq.dao;

import com.labouriq.db.DBConnection;
import com.labouriq.model.Application;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    public List<Application> findByJobId(int jobId) throws SQLException {
        List<Application> list = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE job_id = ? ORDER BY applied_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public boolean updateStatus(int applicationId, String newStatus) throws SQLException {
        String sql = "UPDATE applications SET status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, applicationId);
            return ps.executeUpdate() > 0;
        }
    }

    private Application mapRow(ResultSet rs) throws SQLException {
        Application a = new Application();
        a.setId(rs.getInt("id"));
        a.setJobId(rs.getInt("job_id"));
        a.setSeekerId(rs.getInt("seeker_id"));
        a.setResumePath(rs.getString("resume_path"));
        a.setCoverLetter(rs.getString("cover_letter"));
        a.setStatus(rs.getString("status"));
        String applied = rs.getString("applied_at");
        if (applied != null) a.setAppliedAt(LocalDateTime.parse(applied));
        return a;
    }
}
