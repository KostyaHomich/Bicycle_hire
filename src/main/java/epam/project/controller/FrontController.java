package epam.project.controller;

import epam.project.command.Command;
import epam.project.command.CommandProvider;
import epam.project.dto.ResponseContent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "index", urlPatterns = "/")
public class FrontController  extends HttpServlet {

    private static final Logger LOGGER=LogManager.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandParameter = request.getParameter("command");
        LOGGER.info("command = "+commandParameter);
        Command command = CommandProvider.getInstance().takeCommand(commandParameter);

        ResponseContent responseContent = command.execute(request);
        if (responseContent.getRouter().getType().equals("redirect")) {
            response.sendRedirect(responseContent.getRouter().getRoute());
        } else {
            request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
        }
    }
}
