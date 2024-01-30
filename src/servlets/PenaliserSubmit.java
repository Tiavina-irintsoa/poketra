package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Penalisation;

public class PenaliserSubmit extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idFournisseur = req.getParameter("fournisseur");
            String idCause = req.getParameter("cause");
            String date = req.getParameter("date");

            Penalisation p = new Penalisation(idFournisseur, idCause, date);
            p.insert(null);
            resp.sendRedirect("penaliser?fournisseur="+idFournisseur);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
