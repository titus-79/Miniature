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
    private final List<Post> posts = new ArrayList<>(List.of(
            new Post(Post.genererID(), "Premier post", "Contenu du premier post", LocalDateTime.now(), false),
            new Post(Post.genererID(), "Deuxième post", "Contenu du deuxième post", LocalDateTime.now(), false)));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        User userSession = (User) req.getSession().getAttribute("user");
        // posts = List.of(
        // new Post(1L, "Premier post", "Contenu du premier post", LocalDateTime.now(),
        // true),
        // new Post(2L, "Deuxième post", "Contenu du deuxième post",
        // LocalDateTime.now(), true));

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

        List<Post> postsTries = new ArrayList<>(posts);
        postsTries.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        req.setAttribute("posts", postsTries);
        req.setAttribute("typeActif", type);
        req.setAttribute("feedTitre", type.equals("abonne") ? "Fil abonnements" : "Fil recommandations");

        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        boolean champsValides = title != null && !title.isBlank()
                && content != null && !content.isBlank();

        if (champsValides) {
            posts.add(new Post(Post.genererID(), title, content, LocalDateTime.now(), false));
            resp.sendRedirect("/feed");
        } else {
            resp.sendRedirect("/feed");
        }

    }
}
