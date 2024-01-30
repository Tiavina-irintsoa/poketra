package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.CausePenalisation;

public class ListeCausePenalisation extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("liste", CausePenalisation.list(null));
            RequestDispatcher dispat = req.getRequestDispatcher("liste_cause_penalisation.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
