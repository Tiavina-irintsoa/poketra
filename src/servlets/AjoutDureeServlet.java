package servlets;

import java.io.IOException;
import java.sql.*;

import connexion.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.*;
import mapping.Type;
import util.FonctionGenraliser;
public class  AjoutDureeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connect con  = new Connect();
            Connection connection = con.getConnectionPostgresql();
            try {
            System.out.println("coucou");
             req.setAttribute("types", Type.getAll(connection));
             req.setAttribute("matieres", Matiere.list(connection));
             req.setAttribute("looks", FonctionGenraliser.selectGeneraliser(new Look(),"look",connection));
             req.setAttribute("dimensions", Dimension.getAll(connection));
            RequestDispatcher dispat = req.getRequestDispatcher("duree.jsp");
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
