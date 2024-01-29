package servlets;

import java.io.IOException;
import java.sql.Connection;

import connexion.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import profil.Profil;
import util.FonctionGenraliser;

public class AjouterMultiplicateurServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connect con  = new Connect();
            Connection connection = con.getConnectionPostgresql();
            try {
                System.out.println("coucou");
                req.setAttribute("profils", FonctionGenraliser.selectGeneraliser(new Profil(), "profil", connection) );
                RequestDispatcher dispat = req.getRequestDispatcher("multiplicateur_par_profil.jsp");
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
