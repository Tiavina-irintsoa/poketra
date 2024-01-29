package servlets;

import java.io.IOException;
import java.util.Vector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.FicheLook;
import mapping.Look;
import mapping.Matiere;

public class AjoutMatiereCompatibleServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            FicheLook flook= new FicheLook(req.getParameter("look"));
            Vector<Matiere> tousLesNonCompatible=flook.getMatieresNonCompatible(null);
            System.out.println(tousLesNonCompatible.size());
            req.setAttribute("matiereNonCompatible",tousLesNonCompatible);
            req.setAttribute("look",req.getParameter("look"));
            RequestDispatcher dispat = req.getRequestDispatcher("ajout_matiere_compatible.jsp");
            System.out.println("tsy tonga");
            dispat.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}   
