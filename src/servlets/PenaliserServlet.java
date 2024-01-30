package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.CausePenalisation;

public class PenaliserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fournisseur = req.getParameter("fournisseur");
            req.setAttribute(
                "fournisseur", fournisseur);
            req.setAttribute("causes", CausePenalisation.list(null));
                RequestDispatcher dispat = req.getRequestDispatcher("penaliser.jsp");
                dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
