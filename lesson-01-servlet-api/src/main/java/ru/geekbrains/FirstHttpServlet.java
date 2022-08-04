package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/first-http-servlet/*")
public class FirstHttpServlet extends HttpServlet {
    // так делать не очень правильно. Потому что этот репозиторий должен присутствовать в одном экземпляре и чтобы все кто работают с этим репозиторием пользовались с одним и тем же экземпляром этого репозитория.
    // Можно было бы реализовать его с помощью паттерна SingleTon, но сервлеты предоставляют нам возможность по части создания классов, которые всем нужны и по части выполнения каких либо действий при инициализации нашего приложения. это Listener
//    private UserRepository repository = new UserRepository();


    private UserRepository userRepository;

    // вызывается при создании данного сервлета.
    @Override
    public void init() throws ServletException {
        // берем атрибут из ServletContext
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("<h1>Привет от сервлета!!!</h1>");
        // эта та часть url, которая отличает наше приложение на сервере от остальных (servlet-app)
        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p");

        // то что идентифицирует наш сервлет (/first-http-servlet)
        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p");

        // это все что мы можем указать после /first-http-servlet/
        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p");

        // в url мы можем указывать так называемые gap параметры
        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p");

        // http://localhost:8080/servlet-app/first-http-servlet?param1=val1
        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p");

        //http:localhost:8080/servlet-app/first-http-servlet?param1=val1&param2=val2
        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p");


    }
}
