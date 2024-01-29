package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.FicheLook;

public class FicheLookServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idLook = req.getParameter("look");
        FicheLook ficheLook=new FicheLook(idLook);
        try {
            ficheLook.completData(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("look", ficheLook);
        RequestDispatcher dispat = req.getRequestDispatcher("fiche_look.jsp");
        dispat.forward(req, resp);
    }
}
