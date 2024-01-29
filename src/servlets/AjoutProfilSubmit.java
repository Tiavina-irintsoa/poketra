package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Type;
import profil.Profil;
import util.FonctionGenraliser;

import java.io.IOException;

public class AjoutProfilSubmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nom = req.getParameter("nomProfil");
            Profil profil =new Profil( nom);
            FonctionGenraliser.insertGeneralMysqlPostG(profil,null,"DEFAULT");
            resp.sendRedirect("ajoutProfilServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
