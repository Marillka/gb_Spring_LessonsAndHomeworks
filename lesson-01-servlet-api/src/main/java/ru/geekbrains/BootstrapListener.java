package ru.geekbrains;

import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

// Listener - который срабатывает при загрузке приложения.
// ServletContextListener - listener, который будет срабатывать при инициализации нашего приложения и перед тем как будут создаваться какие либо сервелеты, будет вызван contextInitialized.
@WebListener
public class BootstrapListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // как сделать так чтобы UserRepository был доступен остальным сервлетам. В ServletContext осуществляются все межкомпонентные взаимодействия
         ServletContext sc = sce.getServletContext();

        // создадим экземпляр репозитория
        UserRepository userRepository = new UserRepository();

        // теперь можно пойти в любой сервлет
        sc.setAttribute("userRepository", userRepository);

        userRepository.insert(new User("user1"));
        userRepository.insert(new User("user2"));
        userRepository.insert(new User("user3"));

    }
}
