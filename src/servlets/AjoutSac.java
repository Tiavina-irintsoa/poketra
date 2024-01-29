package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Dimension;
import mapping.Look;
import mapping.Type;
import util.FonctionGenraliser;

public class AjoutSac extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("dimensions", Dimension.getAll(null));
            req.setAttribute("types", Type.getAll(null));
            req.setAttribute("looks", FonctionGenraliser.selectGeneraliser(new Look(), "look", null));
            RequestDispatcher dispat = req.getRequestDispatcher("ajout_sac.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
