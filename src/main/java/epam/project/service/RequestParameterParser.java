package epam.project.service;

import epam.project.command.CommandUpdateUser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestParameterParser {
    private static final Logger LOGGER = LogManager.getLogger(RequestParameterParser.class);
    public static Map<String, String> parseParameters(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();

        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String key = parameterNames.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }

        return map;
    }
}
