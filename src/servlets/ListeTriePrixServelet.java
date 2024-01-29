package servlets;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import search.RecherchePrix;

public class ListeTriePrixServelet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RecherchePrix recherche = new RecherchePrix(req.getParameter("min"), req.getParameter("max"));
            recherche.completeResult(null);
            req.setAttribute("recherche", recherche);
            RequestDispatcher dispat = req.getRequestDispatcher("list_par_prix.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
