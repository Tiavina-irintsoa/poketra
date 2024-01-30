package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.ValeurPenalisation;

public class ModifierCauseSubmit extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCause = req.getParameter("idCause");
        String newValue = req.getParameter("valeur");
        ValeurPenalisation nouvelle = new ValeurPenalisation(idCause, newValue);
        try {
            nouvelle.insert();
            resp.sendRedirect("listCause");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
