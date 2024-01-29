package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import fabrication.FabricationSac;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FabricationSacSubmit extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
           String sac = req.getParameter("sac");
            String quantiter = req.getParameter("quantite");
            String date = req.getParameter("date");
            FabricationSac f = new FabricationSac(sac,quantiter, date);
            f.fabriquer();
            resp.sendRedirect("fabricationSac");
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            out.print(e.getMessage());
            e.printStackTrace();
        }
    }
}
