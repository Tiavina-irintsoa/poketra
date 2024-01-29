package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stats.StatistiqueVenteGlobal;

public class StatVenteGlobalServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StatistiqueVenteGlobal stat = new StatistiqueVenteGlobal(req.getParameter("annee"), req.getParameter("sac"));
            stat.completeData();
            req.setAttribute("stat", stat);
            RequestDispatcher dispat = req.getRequestDispatcher("statistique_vente.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
