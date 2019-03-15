package epam.project.filter;

import epam.project.command.CommandType;
import epam.project.command.PageConst;
import epam.project.controller.FrontController;
import epam.project.dto.Restrictions;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/")
public class RestrictionFilter implements Filter {

    private static final String SIGN_IN_USER = "signInUser";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Restrictions restrictions = CommandType
                .of(httpServletRequest.getParameter("command"))
                .orElse(CommandType.SHOW_MAIN_PAGE)
                .getRestrictions();


        User sessionUser = (User) (httpServletRequest.getSession().getAttribute(SIGN_IN_USER));
        String role = (sessionUser == null) ? UserRole.ANON.name() : sessionUser.getRole();
        if (restrictions.isAllowedRole(role)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletRequest.getSession().invalidate();
            servletRequest.getRequestDispatcher(PageConst.LOGIN_PAGE_PATH).forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
