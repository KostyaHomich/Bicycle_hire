package epam.project.service.builder;

import epam.project.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Builder<T> {
    T build(Map params) throws ServiceException, UnsupportedEncodingException;
}
