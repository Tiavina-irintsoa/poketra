package servlets;

import java.io.IOException;
import java.sql.Connection;

import connexion.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Matiere;
import mapping.Sac;

public class AjoutFormuleServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
        Connect con  = new Connect();
        Connection connection = con.getConnectionPostgresql();
        try {
        System.out.println("coucou");
        req.setAttribute("sacs", Sac.list(connection));
         req.setAttribute("matieres", Matiere.list(connection));
        RequestDispatcher dispat = req.getRequestDispatcher("confection.jsp");
        dispat.forward(req, resp);
       } catch (Exception e) {
        throw e;
       }
       finally{
        connection.close();
       }
       } catch (Exception e) {
        e.printStackTrace();
       }
    }
}
