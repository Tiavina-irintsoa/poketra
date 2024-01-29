package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.DureeFabrication;

public class AjoutDureeSubmitServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DureeFabrication duree = new DureeFabrication(req.getParameter("look"), req.getParameter("type"), req.getParameter("dimension"), req.getParameter("duree"));
            duree.insert(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
