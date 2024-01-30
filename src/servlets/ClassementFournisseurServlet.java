package servlets;

import java.io.IOException;

import classement.ClassementFournisseur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClassementFournisseurServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String classement = req.getParameter("classement");
        String matiere = req.getParameter("matiere");
        ClassementFournisseur c = new ClassementFournisseur(matiere, classement);
        try {
            c.getResults(null);
            req.setAttribute("classement", c);
            RequestDispatcher dispat = req.getRequestDispatcher("classement_fournisseur.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
