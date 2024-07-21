package org.app.servlets;

import org.app.models.Choice;
import org.app.worker.ConfigWorker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/dialog/")
public class DialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id.equals("-1")) {
            req.getSession().setAttribute("gameCount", (Integer) req.getSession().getAttribute("gameCount") + 1);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("../jspFiles/gameOver.jsp");
            requestDispatcher.forward(req, resp);
        } else if (id.equals("-2")) {
            req.getSession().setAttribute("gameCount", (Integer) req.getSession().getAttribute("gameCount") + 1);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("../jspFiles/victory.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            ConfigWorker configWorker = new ConfigWorker();
            String question = configWorker.getQuestionText(id);
            req.setAttribute("question", question);
            List<Choice> choices = configWorker.getChoices(id);
            req.setAttribute("choices", choices);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("../jspFiles/dialog.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}

