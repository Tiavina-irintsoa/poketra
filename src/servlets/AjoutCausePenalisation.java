package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AjoutCausePenalisation extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestDispatcher dispat = req.getRequestDispatcher("ajout_cause_penalisation.jsp");
        dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
