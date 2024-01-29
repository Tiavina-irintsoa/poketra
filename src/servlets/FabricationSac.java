package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Sac;

public class FabricationSac extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
       
        req.setAttribute("sacs", Sac.list(null));
        RequestDispatcher dispat = req.getRequestDispatcher("fabrication_sac.jsp");
        dispat.forward(req, resp);
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}