package com.example.jmtpfinal23050;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String umail = request.getParameter("email");
        String upwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher disp = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/com23050?useSSL=false", "root", "awgdelfin");
            final String QUERY = "select * from users where email = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(QUERY);
            ps.setString(1, umail);
            ps.setString(2, upwd);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                session.setAttribute("email", rs.getString(2));
                disp = request.getRequestDispatcher("index.jsp");
            } else {
                request.setAttribute("status", "failed");
                disp = request.getRequestDispatcher("login.jsp");
            }
            disp.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
