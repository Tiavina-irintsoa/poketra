package servlets;

import connexion.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Dimension;
import mapping.Look;
import mapping.Matiere;
import mapping.Sac;
import mapping.Type;
import util.FonctionGenraliser;

import java.io.IOException;
import java.sql.Connection;

public class PrixDeVenteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connect con  = new Connect();
            Connection connection = con.getConnectionPostgresql();
            try {                
                req.setAttribute("sacs", Sac.list(connection));
                RequestDispatcher dispat = req.getRequestDispatcher("prix_de_vente.jsp");
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
