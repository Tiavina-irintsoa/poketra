package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangerPrix extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("idMatiere", req.getAttribute("matiere"));
        RequestDispatcher dispat = req.getRequestDispatcher("modifier_prix.jsp");
        dispat.forward(req, resp);
    }
}
