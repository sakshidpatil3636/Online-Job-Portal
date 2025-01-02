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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String em = req.getParameter("email");
            String ps = req.getParameter("password");
            User u = new User();
            HttpSession session = req.getSession();

            // Check if the user is admin
            if ("admin@gmail.com".equals(em) && "admin@123".equals(ps)) {
                session.setAttribute("userobj", u);
                u.setRole("admin");
                resp.sendRedirect("admin.jsp");
            } else {
                // Check user login through DAO
                UserDAO dao = new UserDAO(DBConnect.getconn());
                User user = dao.login(em, ps);

                if (user != null) {
                    session.setAttribute("userobj", user);
                    resp.sendRedirect("home.jsp");
                } else {
                    session.setAttribute("succMsg", "Invalid Email or Password");
                    resp.sendRedirect("login.jsp");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
