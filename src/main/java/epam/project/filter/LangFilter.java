package epam.project.filter;

import epam.project.util.CookieFinder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

@WebFilter(urlPatterns = "/*")
public class LangFilter implements Filter {

    private static final String LANG_PARAM = "lang";
    private static final Logger LOGGER = LogManager.getLogger(LangFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String change = request.getParameter(LANG_PARAM);

        if (change != null) {
            changeLocale(request, response, change);
        } else {
            initLocale(request);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void changeLocale(HttpServletRequest request, HttpServletResponse response, String lang) {

        Optional<LanguageEnum> languageEnum = LanguageEnum.fromString(lang);
        Locale locale = languageEnum.map(LanguageEnum::getLocaleByEnum).orElseGet(() -> LanguageEnum.getLocaleByEnum(LanguageEnum.EN));

        Cookie langCookie = new Cookie(LANG_PARAM, locale.getLanguage());
        langCookie.setPath(request.getContextPath());

        request.getSession().setAttribute(LANG_PARAM, locale);

        response.addCookie(langCookie);

    }

    private void initLocale(HttpServletRequest request) {

        Optional<String> cookieLang = CookieFinder.getValueByName(LANG_PARAM, request.getCookies());
        Supplier<Locale> supplier = () -> LanguageEnum.getLocaleByEnum(LanguageEnum.EN);


        if (cookieLang.isPresent()) {
            Optional<LanguageEnum> languageEnum = LanguageEnum.fromString(cookieLang.get());
            Locale locale = languageEnum.map(LanguageEnum::getLocaleByEnum).orElseGet(supplier);


            request.getSession().setAttribute(LANG_PARAM, locale);

        } else {
            Optional<LanguageEnum> languageEnum = LanguageEnum.fromString(request.getLocale().getLanguage());
            Locale locale;
            locale = languageEnum.map(LanguageEnum::getLocaleByEnum).orElseGet(supplier);

            request.getSession().setAttribute(LANG_PARAM, locale);

        }
    }


    @Override
    public void destroy() {
    }
}
