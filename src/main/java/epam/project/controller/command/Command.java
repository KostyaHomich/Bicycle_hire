package epam.project.controller.command;

import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    ResponseContent execute(HttpServletRequest request);
}
