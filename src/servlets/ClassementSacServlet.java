package servlets;

import java.io.IOException;

import classement.ClassementSac;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClassementSacServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        String classement = req.getParameter("classement");
        ClassementSac c = new ClassementSac(classement);
        
        c.completeData(null);
        String page="classement_vente.jsp";
        req.setAttribute("classement", c);
        if(c.getClassement()!=0){
            page="classement_rentabilite.jsp";
        }
        RequestDispatcher dispat = req.getRequestDispatcher(page);
        dispat.forward(req, resp);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}



