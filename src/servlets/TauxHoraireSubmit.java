package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Type;
import profil.TauxHoraireBase;
import util.FonctionGenraliser;

import java.io.IOException;

public class TauxHoraireSubmit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String taux = req.getParameter("taux");
            TauxHoraireBase tauxHoraireBase=new TauxHoraireBase(Double.parseDouble(taux));
            FonctionGenraliser.insertGeneralMysqlPostG(tauxHoraireBase,null,"DEFAULT");
            resp.sendRedirect("tauxHoraireServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
