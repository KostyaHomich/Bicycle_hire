package epam.project.controller;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.CommandProvider;
import epam.project.dto.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxController  extends  HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String commandParameter = request.getParameter("command");
            Command command = CommandProvider.getInstance().takeCommand(commandParameter);
            ResponseContent responseContent = command.execute(request);

            if (responseContent.getRouter().getType().equals("redirect")) {
                response.sendRedirect(responseContent.getRouter().getRoute());
            } else {
                request.getRequestDispatcher(responseContent.getRouter().getRoute()).forward(request, response);
            }
        }
        catch (CommandException e) {
            throw new ServletException(e);
        }
    }
}
