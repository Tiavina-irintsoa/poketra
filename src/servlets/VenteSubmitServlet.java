package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vente.Vente;

public class VenteSubmitServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
        String idCLient = req.getParameter("idClient");
        String[] sacs= req.getParameterValues("sac");
        String[] qtes= req.getParameterValues("qte");
        System.out.println(sacs.length);
        System.out.println(qtes.length);
        String date= req.getParameter("date");
        Vente v= new Vente(idCLient, date, sacs, qtes);
        v.insert();
        resp.sendRedirect("listClient");
       } catch (Exception e) {
       e.printStackTrace();
       }
    }
}
