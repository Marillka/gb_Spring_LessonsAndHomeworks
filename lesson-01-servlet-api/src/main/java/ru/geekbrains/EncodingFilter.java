package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// В чем отличие фильтра от сервлета. В случае с сервлетом мы обрабатываем запрос и отправляем некоторый ответ. В случае с фильтром, помимо запроса и ответа, к нам  еще приходит параметр FilterChain с помощью которого мы можем пропусть запрос куда то дальше, чтобы он шел до какого нибудь сервлета, либо можем обновить.

// должны указать паттерн. "/*" - означает что через фильтр должны проходить все запросы, которые приходят в наше приложение.
@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(servletRequest, servletResponse);// если написать вот ток, то фильтр просто будет пропускать через себя запросы.

        // хотим чтобы запрос имел определенную кодировку
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
