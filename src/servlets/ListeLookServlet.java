package servlets;

import java.io.IOException;
import java.util.Vector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Look;
import util.FonctionGenraliser;

public class ListeLookServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("liste", FonctionGenraliser.selectGeneraliser(new Look(),"look",null));
            RequestDispatcher dispat = req.getRequestDispatcher("liste_look.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
