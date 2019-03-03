package epam.project.service.builder;

import epam.project.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface Builder<T> {
    T build(HttpServletRequest request) throws ServiceException, UnsupportedEncodingException;
}
