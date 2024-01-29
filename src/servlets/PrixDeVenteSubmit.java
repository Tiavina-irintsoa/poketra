package servlets;

import java.io.IOException;

import finance.PrixVente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PrixDeVenteSubmit extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String pv=req.getParameter("pv");

            PrixVente pVente = new PrixVente(req.getParameter("sac"), pv);
            pVente.modifierPrix();
            resp.sendRedirect("prixDeVente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
