package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Matiere;

import java.io.IOException;

public class ChangerPrixSubmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String prix = req.getParameter("prix");
            String id = req.getParameter("matiere");
            Matiere matiere = new Matiere(id, prix, false);
            matiere.modifierPrix();
            resp.sendRedirect("ajoutMatiere");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
