package com.dao;

import java.sql.ResultSet;
import com.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    private Connection conn;

    public UserDAO(Connection connection) {
        super();
        this.conn = connection;
    }

    // Add User Method with Email Validation
    public boolean addUser(User u) {
        boolean f = false;
        try {
            // Check if the email already exists
            if (isEmailExists(u.getEmail())) {
                System.out.println("Email already exists!");
                return f;
            }

            String sql = "INSERT INTO user(name, qualification, email, password, role) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getQualification());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, "user");

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // User Login Method
    public User login(String email, String password) {
        User u = null;
        try {
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setQualification(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setRole(rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    // Update User Method with Email Validation
    public boolean updateUser(User u) {
        boolean f = false;
        try {
            // Check if the email already exists for another user
            if (isEmailExistsForAnotherUser(u.getEmail(), u.getId())) {
                System.out.println("Email already exists for another user!");
                return f;
            }

            String sql = "UPDATE user SET name=?, qualification=?, email=?, password=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getName());
            ps.setString(2, u.getQualification());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Helper Method to Check if Email Already Exists
    private boolean isEmailExists(String email) {
        boolean exists = false;
        try {
            String sql = "SELECT id FROM user WHERE email=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Helper Method to Check if Email Exists for Another User
    private boolean isEmailExistsForAnotherUser(String email, int userId) {
        boolean exists = false;
        try {
            String sql = "SELECT id FROM user WHERE email=? AND id<>?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
}
