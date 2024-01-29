package servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.FormuleConfection;

public class AjoutFormuleSubmitServlet extends HttpServlet{
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sac = req.getParameter("sac");
            Enumeration<String> parameterNames = req.getParameterNames();

            // Créer un HashMap pour stocker les noms et les valeurs des paramètres
            HashMap<String, String> paramMap = new HashMap<>();

            // Parcourir les paramètres de la requête
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = req.getParameter(paramName);
                if(paramName.startsWith("quantite-")){
                    paramName = paramName.substring(9);
                    paramMap.put(paramName, paramValue);
                }                
            }
            FormuleConfection formule  = new FormuleConfection(sac,paramMap);
            formule.insertAll(null);
            resp.sendRedirect("ajoutFormule");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
