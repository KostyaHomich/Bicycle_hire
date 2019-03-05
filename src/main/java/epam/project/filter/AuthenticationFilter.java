package epam.project.filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(AuthenticationFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        LOGGER.info("FILTER"+httpServletRequest.getParameter("command"));
        //Provide your code here

        //Don't forget to invoke this method
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
