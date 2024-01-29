package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Matiere;

public class AjoutMatiereSubmit extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nom = req.getParameter("nom");
            Matiere matiere=new Matiere(nom, 0, req.getParameter("prix"));
            matiere.insert(null);
            resp.sendRedirect("ajoutMatiere");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
