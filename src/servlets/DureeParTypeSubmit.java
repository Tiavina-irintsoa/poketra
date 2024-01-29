package servlets;

import finance.TypeDuree;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DureeParTypeSubmit extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String type = req.getParameter("type");
            String duree= req.getParameter("duree");
            String rh= req.getParameter("rh");
            TypeDuree typeD=new TypeDuree(type,duree,rh);
            typeD.insert(null);
            resp.sendRedirect("dureeType");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
