package com.labouriq.dao;

import com.labouriq.model.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    private final String URL = "jdbc:mysql://localhost:3306/labouriq_db";
    private final String USER = "root";
    private final String PASS = "Sufi$&8416@";

    // APPLY JOB
    public boolean applyJob(Application app) throws Exception {
        String sql = "INSERT INTO applications (job_id, seeker_id) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, app.getJobId());
            ps.setInt(2, app.getSeekerId());
            ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            return false; // already applied
        }
    }

    // GET APPLICATIONS FOR EMPLOYER JOB
    public List<Application> getApplicationsByJob(int jobId) throws Exception {
        List<Application> list = new ArrayList<>();

        String sql = "SELECT * FROM applications WHERE job_id=?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Application(
                        rs.getInt("id"),
                        rs.getInt("job_id"),
                        rs.getInt("seeker_id"),
                        rs.getString("status"),
                        rs.getTimestamp("applied_at").toLocalDateTime()
                ));
            }
        }
        return list;
    }

    // UPDATE STATUS
    public void updateStatus(int appId, String status) throws Exception {
        String sql = "UPDATE applications SET status=? WHERE id=?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, appId);
            ps.executeUpdate();
        }
    }

    // JOB SEEKER STATUS VIEW
    public List<Application> getMyApplications(int seekerId) throws Exception {
        List<Application> list = new ArrayList<>();

        String sql = "SELECT * FROM applications WHERE seeker_id=?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, seekerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Application(
                        rs.getInt("id"),
                        rs.getInt("job_id"),
                        rs.getInt("seeker_id"),
                        rs.getString("status"),
                        rs.getTimestamp("applied_at").toLocalDateTime()
                ));
            }
        }
        return list;
    }

    // ================== ADDED FOR CONTROLLER COMPATIBILITY ==================
    public boolean apply(int jobId, int seekerId) throws Exception {
        Application app = new Application();
        app.setJobId(jobId);
        app.setSeekerId(seekerId);
        return applyJob(app);
    }

    public static class DBConnection {

        private static final String URL =
                "jdbc:mysql://localhost:3306/labouriq_db?useSSL=false&serverTimezone=UTC";

        private static final String USER = "root";
        private static final String PASSWORD = "Sufi$&8416@";

        static {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("MySQL Driver not found", e);
            }
        }

        public static Connection getConnection() throws Exception {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
}
