package com.example.jmtpfinal23050;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "ShoppingCartController", value = "/ShoppinCart-Controller")

public class ShoppingCartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uname = request.getParameter("nombre");
        String usurname = request.getParameter("apellido");
        String uemail = request.getParameter("mail");
        String ucant = request.getParameter("cantidad");
        String ucategoria = request.getParameter("categoria");
        String utotalpago = request.getParameter("totalpago");

        RequestDispatcher disp = null;
        Connection con = null;


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/com23050?useSSL=false", "root", "awgdelfin");
            final String STATEMENT = "insert into buyers (name, surname, email, cant,category,amount) values (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(STATEMENT);
            pst.setString(1, uname);
            pst.setString(2, usurname);
            pst.setString(3, uemail);
            pst.setString(4, ucant);
            pst.setString(5, ucategoria);
            pst.setString(6, utotalpago);
            int rowCount = pst.executeUpdate();
            disp = request.getRequestDispatcher("comprartickets.jsp");
            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }

            disp.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}