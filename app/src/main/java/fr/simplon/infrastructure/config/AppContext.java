package fr.simplon.infrastructure.config;

import fr.simplon.application.services.AuthService;
import fr.simplon.application.services.FeedService;
import fr.simplon.application.services.PostService;
import fr.simplon.application.useCases.AddCommentUseCase;
import fr.simplon.application.useCases.FollowUserUseCase;
import fr.simplon.application.useCases.LikePostUseCase;
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
        FollowUserUseCase followUserUseCase = new FollowUserUseCase(userRepository);
        LikePostUseCase likePostUseCase = new LikePostUseCase(postRepository);
        PostService postService = new PostService(postRepository);
        AddCommentUseCase addCommentUseCase = new AddCommentUseCase(postRepository);

        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("authService", authService);
        ctx.setAttribute("feedService", feedService);
        ctx.setAttribute("followUseCase", followUserUseCase);
        ctx.setAttribute("likeUseCase", likePostUseCase);
        ctx.setAttribute("postService", postService);
        ctx.setAttribute("addCommentUseCase", addCommentUseCase);
        System.out.println("[AppContext] Démarré");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[AppContext] Arrêté.");

    }
}
