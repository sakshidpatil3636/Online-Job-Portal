package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.JobDAO;
import com.entity.Jobs;

@WebServlet("/update")
public class UpdateJobServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve form data
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String desc = req.getParameter("desc");

            // Create and populate the Jobs object
            Jobs j = new Jobs();
            j.setId(id);
            j.setTitle(title);
            j.setDescription(desc);
            j.setLocation(location);
            j.setStatus(status);
            j.setCategory(category);

            // Initialize DAO
            JobDAO dao = new JobDAO(DBConnect.getconn());

            // Attempt to update the job
            boolean f = dao.updateJob(j);

            // Handle session messages and redirection
            HttpSession session = req.getSession();
            if (f) {
                session.setAttribute("succMsg", "Job Updated Successfully.");
                resp.sendRedirect("view_job.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong on the server!!");
                resp.sendRedirect("view_job.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
