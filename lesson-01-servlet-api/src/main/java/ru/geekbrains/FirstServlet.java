package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

// Servlet - web компонент, который управляется контейнером и производит динамический контент.
// Чтобы этот класс был найден, есть 2 способа указать на него серверу-приложений. 1) Испльзование файла web.xml (не используется). 2) Написать аннотацию @WebServlet и префикс, который будет касаться нашего сервлета.
@WebServlet("/first-servlet")
public class FirstServlet  implements Servlet {

    // как сервер-приложение будет обращаться с этим сервлетов. Как только он обнаружит этот класс в war-файле, он попытается его создать с помощью конструктора. И после того как он его создаст, он вызовет метод init, через который он передает параметр servletConfig (это главное средство взаимодействия сервлета с сервером-приложений).
    private ServletConfig config;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    // САМЫЙ ВАЖНЫЙ МЕТОД. Каждый раз, когда на наш сервлет будет приходить какой то запрос, будет вызываться этот метод.
    // С помощью servletRequest мы сможем понять что за запрос к нам пришел.
    // С попомщью servletResponse мы сможем как то на этот запрос ответить.
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse .getWriter().println("<h1>Привет от сервлета!!!</h1>");

    }

    // может возвращать любую информацию. Его почти никогда не используют.
    // с ним можно ничего не делать.
    @Override
    public String getServletInfo() {
        return null;
    }

    // будет вызван, когда наше приложение будет завершаться по какой то причине или когда сервер-приложений сам решит завершиться. Кароче здесть пишем то, что нужно сделать при удалении сервлета.
    @Override
    public void destroy() {

    }
}
