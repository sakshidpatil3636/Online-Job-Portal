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

@WebServlet("/delete")
public class DeleteJobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Parse job ID from the request
            int id = Integer.parseInt(req.getParameter("id"));

            // DAO interaction to delete the job
            JobDAO dao = new JobDAO(DBConnect.getconn());
            boolean f = dao.deleteJob(id);

            // Get the session object
            HttpSession session = req.getSession();

            // Set success or error message and redirect
            if (f) {
                session.setAttribute("succMsg", "Job Deleted Successfully.");
                resp.sendRedirect("view_job.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong on the server!");
                resp.sendRedirect("view_job.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
