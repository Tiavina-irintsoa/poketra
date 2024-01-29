package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Sac;

public class AjoutSacSubmit extends HttpServlet{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Sac sac = new Sac(req.getParameter("dimension"), req.getParameter("type"), req.getParameter("look"), req.getParameter("nom"));
            sac.insert(null);
            resp.sendRedirect("ajoutSac");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
