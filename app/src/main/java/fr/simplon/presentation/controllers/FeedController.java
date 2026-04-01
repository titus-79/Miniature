package fr.simplon.presentation.controllers;

import java.io.IOException;
import java.util.List;

import fr.simplon.application.services.FeedService;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")
public class FeedController extends HttpServlet {

    private FeedService feedService;

    @Override
    public void init() {
        this.feedService = (FeedService) getServletContext().getAttribute("feedService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        req.setAttribute("userSession", userSession);

        String type = req.getParameter("type");
        if (type == null || type.isBlank()) {
            type = "recommande";
        }

        List<Post> postsTries = feedService.getRecommendedFeed();

        if (type.equals("abonne")) {
            req.setAttribute("feedTitre", "Fil abonnements");
            req.setAttribute("feedVide", "Vous n'avez pas encore d'abonnements.");

            if (userSession != null) {
                postsTries = feedService.getFollowingFeed(userSession);
            } else {
                postsTries = List.of();
            }
        } else {
            req.setAttribute("feedTitre", "Fil recommandations");
            req.setAttribute("feedVide", "Aucune recommandation pour le moment.");
        }

        req.setAttribute("posts", postsTries);
        req.setAttribute("typeActif", type);

        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
    }

}
