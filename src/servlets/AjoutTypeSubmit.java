package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Type;
import util.FonctionGenraliser;

import java.io.IOException;

public class AjoutTypeSubmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nom = req.getParameter("nomType");
            Type type =new Type( 0,nom);
            FonctionGenraliser.insertGeneralMysqlPostG(type,null,"DEFAULT");
            resp.sendRedirect("tauxHoraireServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
