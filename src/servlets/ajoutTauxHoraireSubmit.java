package servlets;

import finance.TauxHoraire;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Look;
import mapping.TypeRH;
import util.FonctionGenraliser;

import java.io.IOException;

public class ajoutTauxHoraireSubmit extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String rh = req.getParameter("rh");
            String valeur = req.getParameter("taux");
            TypeRH type = new TypeRH(rh, valeur);
            type.insert(null);
            resp.sendRedirect("rh");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
