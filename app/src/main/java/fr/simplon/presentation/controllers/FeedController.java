package fr.simplon.presentation.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.infrastructure.persistence.PostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")
public class FeedController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        req.setAttribute("userSession", userSession);

        String type = req.getParameter("type");
        if (type == null || type.isBlank()) {
            type = "recommande";
        }

        List<Post> postsTries = new ArrayList<>(PostRepository.TOUS);
        postsTries.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));

        if (type.equals("abonne")) {
            req.setAttribute("feedTitre", "Fil abonnements");
            req.setAttribute("feedVide", "Vous n'avez pas encore d'abonnements.");

            if (userSession != null) {
                List<User> following = userSession.getFollowing();
                postsTries = postsTries.stream()
                        .filter(p -> following.stream()
                                .anyMatch(u -> u.getId().equals(p.getAuthor().getId())))
                        .collect(Collectors.toList());
            } else {
                postsTries = new ArrayList<>();
            }
        } else {
            req.setAttribute("feedTitre", "Fil recommandations");
            req.setAttribute("feedVide", "Aucune recommandation pour le moment.");
        }

        req.setAttribute("posts", postsTries);
        req.setAttribute("typeActif", type);

        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        if (title != null && !title.isBlank() && content != null && !content.isBlank()) {
            PostRepository.TOUS.add(
                    new Post(Post.genererID(), title, content,
                            LocalDateTime.now(), false, userSession));
        }

        resp.sendRedirect("/feed");
    }
}
