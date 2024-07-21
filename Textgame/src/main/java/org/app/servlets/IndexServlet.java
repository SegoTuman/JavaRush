package org.app.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer gameCount = (Integer) req.getSession().getAttribute("gameCount");
        if (gameCount == null) {
            req.getSession().setAttribute("gameCount", 0);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jspFiles/index.jsp");
        requestDispatcher.forward(req, resp);
    }

}