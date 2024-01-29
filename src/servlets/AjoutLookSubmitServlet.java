package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Look;
import util.FonctionGenraliser;

public class AjoutLookSubmitServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nomLook = req.getParameter("nom"); 
            Look look=new Look(nomLook,0);
            FonctionGenraliser.insertGeneralMysqlPostG(look,null,"DEFAULT");
        resp.sendRedirect("ajoutLook");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
