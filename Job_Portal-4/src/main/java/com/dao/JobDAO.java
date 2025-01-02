package com.dao;
import java.sql.*;
import com.entity.Jobs;
import java.util.*;
public class JobDAO {

    private Connection conn;

    public JobDAO(Connection connection) {
        super();
        this.conn = connection; 
    }

    // Add Job Method
    public boolean addJobs(Jobs job) {
        boolean f = false;
        try {
            String sql = "INSERT INTO jobs(title, description, category, status, location) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Get All Jobs Method
    public List<Jobs> getAllJobs() {
        List<Jobs> list = new ArrayList<Jobs>();
        Jobs job = null;
        try {
            String sql = "SELECT * FROM jobs ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Jobs();
                job.setId(rs.getInt(1));
                job.setTitle(rs.getString(2));
                job.setDescription(rs.getString(3));
                job.setCategory(rs.getString(4));
                job.setStatus(rs.getString(5));
                job.setLocation(rs.getString(6));
                job.setPdate(rs.getString(7));
                list.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get Job by ID Method
    public Jobs getJobById(int id) {
        Jobs job = null;
        try {
            String sql = "SELECT * FROM jobs WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Jobs();
                job.setId(rs.getInt(1));
                job.setTitle(rs.getString(2));
                job.setDescription(rs.getString(3));
                job.setCategory(rs.getString(4));
                job.setStatus(rs.getString(5));
                job.setLocation(rs.getString(6));
                job.setPdate(rs.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }

    // Update Job Method
    public boolean updateJob(Jobs job) {
        boolean f = false;
        try {
            String sql = "UPDATE jobs SET title=?, description=?, category=?, status=?, location=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, job.getTitle());
            ps.setString(2, job.getDescription());
            ps.setString(3, job.getCategory());
            ps.setString(4, job.getStatus());
            ps.setString(5, job.getLocation());
            ps.setInt(6, job.getId());

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Delete Job Method
    public boolean deleteJob(int id) {
        boolean f = false;
        try {
            String sql = "DELETE FROM jobs WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            if (i == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    // Get Jobs by Category or Location Method
    public List<Jobs> getJobsORLocationAndCate(String category, String location) {
        List<Jobs> list = new ArrayList<Jobs>();
        Jobs job = null;
        try {
            String sql = "SELECT * FROM jobs WHERE category=? OR location=? ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Jobs();
                job.setId(rs.getInt(1));
                job.setTitle(rs.getString(2));
                job.setDescription(rs.getString(3));
                job.setCategory(rs.getString(4));
                job.setLocation(rs.getString(5));
                job.setStatus(rs.getString(6));
                job.setPdate(rs.getString(7));
                list.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get Jobs by Category and Location Method
    public List<Jobs> getJobsAndLocationAndCate(String category, String location) {
        List<Jobs> list = new ArrayList<Jobs>();
        Jobs job = null;
        try {
            String sql = "SELECT * FROM jobs WHERE category=? AND location=? ORDER BY id DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ps.setString(2, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                job = new Jobs();
                job.setId(rs.getInt(1));
                job.setTitle(rs.getString(2));
                job.setDescription(rs.getString(3));
                job.setCategory(rs.getString(4));
                job.setLocation(rs.getString(5));
                job.setStatus(rs.getString(6));
                job.setPdate(rs.getString(7));
                list.add(job);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
