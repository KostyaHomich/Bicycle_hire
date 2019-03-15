package epam.project.builder;

import epam.project.service.exception.ServiceException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface Builder<T> {
    T build(Map<String,String> params) throws ServiceException, UnsupportedEncodingException;
}
