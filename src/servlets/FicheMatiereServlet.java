package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.FicheLook;
import mapping.FicheMatiere;

public class FicheMatiereServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idmatiere = req.getParameter("matiere");
        FicheMatiere matiere = new FicheMatiere(idmatiere);

        try {
            matiere.completData(null);
            req.setAttribute("matiere", matiere);
            RequestDispatcher dispat = req.getRequestDispatcher("fiche_matiere.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
