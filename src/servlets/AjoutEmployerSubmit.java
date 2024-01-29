package servlets;

import java.io.IOException;

import employe.Employe;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjoutEmployerSubmit extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String profil = req.getParameter("profil");
            String nom= req.getParameter("nom");
            String date= req.getParameter("date");
            Employe e = new Employe(nom, profil, date);
            e.insert(null);
            resp.sendRedirect("ajoutEmployerServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
