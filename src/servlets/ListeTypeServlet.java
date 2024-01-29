package servlets;

import java.io.IOException;
import java.util.Vector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Type;
import util.FonctionGenraliser;
import mapping.Matiere;

public class ListeTypeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("types", FonctionGenraliser.selectGeneraliser(new Type(),"type",null));
            RequestDispatcher dispat = req.getRequestDispatcher("list_type.jsp");
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}
