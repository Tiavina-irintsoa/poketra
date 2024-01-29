package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.DureeFabrication;

import java.io.IOException;

public class TrieBeneficeSubmit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String max = req.getParameter("max");
            String min = req.getParameter("min");
            resp.sendRedirect("benefice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
