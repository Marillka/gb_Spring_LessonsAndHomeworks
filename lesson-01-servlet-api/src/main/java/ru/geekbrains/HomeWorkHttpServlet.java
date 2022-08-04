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
import java.util.List;

@WebServlet("/users")
public class HomeWorkHttpServlet extends HttpServlet {


    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> all = userRepository.findAll();

        PrintWriter pw = resp.getWriter();

        if (req.getPathInfo() == null) {
            pw.println(" <table>\n" +
                    "  <tr>\n" +
                    "    <th>Username</th>\n" +
                    "    <th>Id</th>\n" +
                    "  </tr>");
            for (User user : all) {
                pw.println("<tr>\n" +
                        "    <td> <a href='" + getServletContext().getContextPath() +"/users/" + user.getId() + "'>" + user.getUsername() + "</a></td>\n" +
                        "    <td>" + user.getId() + "</td>\n" +
                        "  </tr>\n");

            }
            pw.println("</table>");
        }
        else {
            String pathInfo = req.getPathInfo();
            int id = Integer.parseInt(pathInfo.substring(1));
            pw.println(" <table>\n" +
                    "  <tr>\n" +
                    "    <th>Username</th>\n" +
                    "    <th>Id</th>\n" +
                    "  </tr>");
            pw.println("<tr>\n" +
                    "    <td>" + userRepository.findById(id).getUsername() + "</td>\n" +
                    "    <td>" + userRepository.findById(id).getId() + "</td>\n" +
                    "  </tr>\n");
        }

        pw.close();


    }
}
