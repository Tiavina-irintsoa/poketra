package servlets;

import java.io.IOException;
import java.sql.*;

import connexion.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjoutTypeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispat = req.getRequestDispatcher("ajout_type.jsp");
        dispat.forward(req, resp);
    }
}
