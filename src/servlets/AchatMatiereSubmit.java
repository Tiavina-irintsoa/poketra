package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import fabrication.MouvementStockMatiere;

public class AchatMatiereSubmit extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String matieres = req.getParameter("matiere");
            String quantiter = req.getParameter("quantite");
            String date = req.getParameter("date");
            String frns=req.getParameter("frns");
            String prix = req.getParameter("prix");
            String qualite = req.getParameter("qualite");
            
            MouvementStockMatiere utilisationMatiere = new MouvementStockMatiere(matieres, date, quantiter, frns, prix,qualite);
            utilisationMatiere.insert(null);
            resp.sendRedirect("achatMatiere");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
