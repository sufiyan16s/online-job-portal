package com.labouriq.dao;

import com.labouriq.model.User;
import com.labouriq.util.DBConnection;
import com.labouriq.util.PasswordUtil;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserDAO {

    // LOGIN
    public User findByEmailAndPassword(String email, String password) throws Exception {

        String sql = "SELECT * FROM users WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;

            String hashed = rs.getString("password");
            if (!PasswordUtil.verify(password, hashed)) return null;

            return new User(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("resume_path")
            );
        }
    }

    // SIGNUP
    public boolean create(String name, String email, String password, String role) throws Exception {

        String sql = """
                INSERT INTO users (full_name, email, password, role)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, PasswordUtil.hash(password));
            ps.setString(4, role);
            ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    // RESUME
    public void updateResume(int userId, String path) throws Exception {
        String sql = "UPDATE users SET resume_path=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, path);
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }

    public boolean createUser(String name, String email, String rawPassword, String role)
            throws Exception {

        String sql = """
        INSERT INTO users (full_name, email, password, role)
        VALUES (?, ?, ?, ?)
    """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);

            // hash password
            String hashed = PasswordUtil.hash(rawPassword);
            ps.setString(3, hashed);

            ps.setString(4, role);

            ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            // email already exists
            return false;
        }
    }

}
