package servlets;
import search.RechercheBenefice;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Matiere;

import java.io.IOException;

public class ListeBeneficeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String min = req.getParameter("min");
            String max = req.getParameter("max");
            RechercheBenefice rechercheBenefice = new RechercheBenefice(min,max);
            rechercheBenefice.getResults(null);
            req.setAttribute("recherche", rechercheBenefice);
            RequestDispatcher dispat = req.getRequestDispatcher("benefice.jsp");
            dispat.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
