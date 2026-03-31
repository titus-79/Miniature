package fr.simplon.infrastructure.config;

import fr.simplon.application.services.AuthService;
import fr.simplon.application.services.FeedService;
import fr.simplon.domain.repository.IPostRepository;
import fr.simplon.domain.repository.IUserRepository;
import fr.simplon.infrastructure.persistence.InMemoryPostRepository;
import fr.simplon.infrastructure.persistence.InMemoryUserRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        IUserRepository userRepository = new InMemoryUserRepository();
        IPostRepository postRepository = new InMemoryPostRepository(userRepository);
        AuthService authService = new AuthService(userRepository);
        FeedService feedService = new FeedService(postRepository);
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("authService", authService);
        ctx.setAttribute("feedService", feedService);
        System.out.println("[AppContext] Démarré");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[AppContext] Arrêté.");

    }
}
