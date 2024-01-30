package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ModifierCauseServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idCause = req.getParameter("cause");
            req.setAttribute("cause", idCause);
            RequestDispatcher dispat = req.getRequestDispatcher("modifier_cause.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
