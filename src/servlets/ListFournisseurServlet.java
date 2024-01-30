package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Fournisseur;
import util.FonctionGenraliser;

public class ListFournisseurServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("liste", FonctionGenraliser.selectGeneraliser(new Fournisseur(), "fournisseur", null));
            RequestDispatcher dispat = req.getRequestDispatcher("liste_fournisseur.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
