package epam.project.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestParameterParser {
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
