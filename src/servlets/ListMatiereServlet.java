package servlets;

import java.io.IOException;
import java.util.Vector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Look;
import mapping.Matiere;

public class ListMatiereServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("matiere")!=null) {
                req.setAttribute("matieres", Matiere.listone(null,req.getParameter("matiere")));
            }else {
                req.setAttribute("matieres", Matiere.list(null));
            }
            RequestDispatcher dispat = req.getRequestDispatcher("list_matiere.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}
