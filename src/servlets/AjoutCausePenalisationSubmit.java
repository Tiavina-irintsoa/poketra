package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.CausePenalisation;

public class AjoutCausePenalisationSubmit extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String valeur = req.getParameter("valeur");
            String cause = req.getParameter("cause");
            CausePenalisation nouvelle = new CausePenalisation(cause, valeur);
            nouvelle.insert();
            resp.sendRedirect("ajoutCausePenalisation");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
