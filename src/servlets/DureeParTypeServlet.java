package servlets;

import connexion.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Type;
import mapping.TypeRH;

import java.io.IOException;
import java.sql.Connection;

public class DureeParTypeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connect con  = new Connect();
            Connection connection = con.getConnectionPostgresql();
            try {
                System.out.println("coucou");
                req.setAttribute("types", Type.getAll(connection));
                req.setAttribute("rh", TypeRH.getAll(connection));
                RequestDispatcher dispat = req.getRequestDispatcher("duree_par_type.jsp");
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
