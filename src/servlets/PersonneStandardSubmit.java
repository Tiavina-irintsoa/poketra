package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.Matiere;

import java.io.IOException;

import finance.ParamDimensionRH;

public class PersonneStandardSubmit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nb = req.getParameter("nb");
            String multi = req.getParameter("multi");
            String rh = req.getParameter("rh");
            ParamDimensionRH param = new ParamDimensionRH(nb, multi,rh);
            param.insert();
            resp.sendRedirect("personneStandard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
