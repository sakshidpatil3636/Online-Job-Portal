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

@WebServlet("/add_job")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Retrieve form data
            String title = req.getParameter("title");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String status = req.getParameter("status");
            String desc = req.getParameter("desc");

            // Create a job entity and set its fields
            Jobs j = new Jobs();
            j.setTitle(title);
            j.setDescription(desc);
            j.setLocation(location);
            j.setStatus(status);
            j.setCategory(category);

            // Get the session object
            HttpSession session = req.getSession();

            // DAO interaction for adding the job
            JobDAO dao = new JobDAO(DBConnect.getconn());
            boolean f = dao.addJobs(j);

            // Redirect and set session message based on the operation result
            if (f) {
                session.setAttribute("succMsg", "Job Posted Successfully.");
                resp.sendRedirect("add_job.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong on the server!");
                resp.sendRedirect("add_job.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
