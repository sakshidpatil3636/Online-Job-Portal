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

@WebServlet("/add_user")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieving form data
            String name = req.getParameter("name");
            String qua = req.getParameter("qualification");
            String email = req.getParameter("email");
            String ps = req.getParameter("password");

            // Creating a User object
            User u = new User(name, email, ps, qua, "User");

            // Initializing DAO
            UserDAO dao = new UserDAO(DBConnect.getconn());

            // Attempt to add the user
            boolean f = dao.addUser(u);

            // Handling session and redirection
            HttpSession session = req.getSession();
            if (f) {
                session.setAttribute("succMsg", "Registered Successfully");
                resp.sendRedirect("signup.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong!");
                resp.sendRedirect("signup.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
