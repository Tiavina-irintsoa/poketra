package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Fournisseur;
import mapping.Type;
import util.FonctionGenraliser;

import java.io.IOException;

public class AjoutFournisseurSubmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nom = req.getParameter("nom");
            String contact=req.getParameter("contact");
            Fournisseur fournisseur=new Fournisseur(nom,contact);
            FonctionGenraliser.insertGeneralMysqlPostG(fournisseur,null,"DEFAULT");
            resp.sendRedirect("ajoutFournisseur");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
