package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.FicheLook;

public class AjoutMatiereCompatibleSubmitServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String[] matieres = req.getParameterValues("matiere[]");
            String idlook = req.getParameter("look");
            System.out.println(idlook);
            FicheLook flook= new FicheLook(idlook,matieres);
            flook.insertMatiereCompatibles(null);
            resp.sendRedirect("listeLook");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
