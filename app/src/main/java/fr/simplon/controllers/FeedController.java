package fr.simplon.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.simplon.models.Post;
import fr.simplon.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")

public class FeedController extends HttpServlet {
    // List<Post> posts = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        User userSession = (User) req.getSession().getAttribute("user");
        List<Post> posts = List.of(
                new Post(1L, "Premier post", "Contenu du premier post", LocalDateTime.now(), true),
                new Post(2L, "Deuxième post", "Contenu du deuxième post", LocalDateTime.now(), true));
                // Long id, String title, String content, LocalDateTime createdAt, boolean isDraft

        String type = req.getParameter("type");
        // Jeu de données statique pour tester
        if (type == null || type.isBlank()) {
            type = "recommande";
        }
        if (type.equals("abonne")) {
            req.setAttribute("feedTitre", "Fil abonnements");
            req.setAttribute("feedVide", "Vous n'avez pas encore d'abonnements.");
        } else {
            req.setAttribute("feedTitre", "Fil recommandations");
            req.setAttribute("feedVide", "Aucune recommandation pour le moment.");
        }


        req.setAttribute("posts", posts);
        req.setAttribute("typeActif", type);
        req.setAttribute("feedTitre", type.equals("abonne") ? "Fil abonnements" : "Fil recommandations");

        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
    }
}
