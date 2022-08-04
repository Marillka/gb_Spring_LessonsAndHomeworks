package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//преподская версия


@WebServlet(urlPatterns = "/users/*")
public class HomeWorkUserServlet extends HttpServlet {

    // метод compile() - позволяет получить скомпилированное регулярное выражение.
    // (\\d) - проверяет что в круглых скобках может быть только целое число.
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
        // если распарсить не получается, то отправляем ответ 400 (что означает, что запрос неправильный).
        if (!matcher.matches()) {
            pw.println("<p>Bad request</p>");
            resp.setStatus(400);
            return;
        }

        long id = Long.parseLong(matcher.group(1));
        User user = userRepository.findById(id);

        if (user == null) {
            pw.println("<p>User not found</p");
            resp.setStatus(404);
            return;
        }

        pw.println("<p>Id: " + user.getId() + "</p");
        pw.println("<p>Username: " + user.getUsername() + "</p");

        pw.close();
    }
}
