package servlets;

import java.io.IOException;
import java.util.Vector;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Sac;
import stats.StatistiquesParGenre;

public class StatParGenreServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Vector<Sac> all = Sac.list(null);
            String sac = "0";
            if(req.getParameter("sac")!=null){
                sac= req.getParameter("sac");
            }
            req.setAttribute("sacs", all);
            StatistiquesParGenre stat = new StatistiquesParGenre(sac);
            stat.getStat(null);
            req.setAttribute("stat", stat);
            RequestDispatcher dispat = req.getRequestDispatcher("state.jsp");
        dispat.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
