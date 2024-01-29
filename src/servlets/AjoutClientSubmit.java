package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vente.Client;

public class AjoutClientSubmit extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Client client = new Client(req.getParameter("nom"), req.getParameter("genre"));
            client.insert(null);
            resp.sendRedirect("ajoutClient");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}