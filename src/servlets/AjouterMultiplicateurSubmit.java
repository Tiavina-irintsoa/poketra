package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import profil.ParamProfil;

public class AjouterMultiplicateurSubmit extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String profil = req.getParameter("profil");
            String multi= req.getParameter("multi");
            String dureemin= req.getParameter("dureemin");
            String dureemax= req.getParameter("dureemax");

            ParamProfil param = new ParamProfil(profil, dureemin, dureemax, multi);
            param.insert(null);
            resp.sendRedirect("ajoutMultiplicateurServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
