package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    ResponseContent execute(HttpServletRequest request);
}
