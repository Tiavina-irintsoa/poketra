package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import stock.matiere.EtatStockMatiere;
public class EtatStockMatiereServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EtatStockMatiere e = new EtatStockMatiere(req.getParameter("date1"),req.getParameter("date2"));
            e.completeData(null);
            req.setAttribute("etat", e);
            RequestDispatcher dispat = req.getRequestDispatcher("etat_stock_matiere.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
