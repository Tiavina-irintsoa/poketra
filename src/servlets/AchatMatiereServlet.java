package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Fournisseur;
import mapping.Matiere;
import util.FonctionGenraliser;

import java.io.IOException;

public class AchatMatiereServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("matieres", Matiere.getAll(null));
            req.setAttribute("fournisseurs", FonctionGenraliser.selectGeneraliser(new Fournisseur(), "fournisseur", null));
        RequestDispatcher dispat = req.getRequestDispatcher("achat_matiere.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
