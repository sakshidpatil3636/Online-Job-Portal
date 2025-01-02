package com.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

@WebServlet("/update_profile")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve user details from the form
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String qua = req.getParameter("qualification");
            String email = req.getParameter("email");
            String ps = req.getParameter("password");

            // Create and populate the User object
            User u = new User();
            u.setId(id);
            u.setName(name);
            u.setQualification(qua);
            u.setEmail(email);
            u.setPassword(ps);

            // Initialize DAO
            UserDAO dao = new UserDAO(DBConnect.getconn());

            // Attempt to update the user's profile
            boolean f = dao.updateUser(u);

            // Handle session messages and redirection
            HttpSession session = req.getSession();
            if (f) {
                session.setAttribute("succMsg", "Profile Updated Successfully");
                resp.sendRedirect("home.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong on server!!");
                resp.sendRedirect("home.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
