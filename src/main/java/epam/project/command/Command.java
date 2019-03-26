package epam.project.command;

import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    ResponseContent execute(HttpServletRequest request) throws CommandException;
}
