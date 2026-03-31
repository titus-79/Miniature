package fr.simplon.presentation.controllers;

import fr.simplon.domain.entities.Comment;
import fr.simplon.domain.entities.Like;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.infrastructure.persistence.PostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
@WebServlet("/like")
public class LikeController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Comment comment = resolveComment(req, resp);
        if (comment != null) {
            // boolean dejaLike = comment.getLikes().stream()
            //         .anyMatch(l -> l.getUser().getId().equals(userSession.getId()));
            // if (dejaLike) {
            //     comment.getLikes().removeIf(l -> l.getUser().getId().equals(userSession.getId()));
            // } else {
            //     comment.addLike(new Like(Like.genererId(), LocalDateTime.now(), null, userSession));
            // }
            resp.sendRedirect(req.getContextPath() + "/post");
            return;
        }


        Post post = resolvePost(req, resp);
        if (post != null) {
            // boolean dejaLike =  post.getLikes().stream()
            //         .anyMatch(l -> l.getUser().getId().equals(userSession.getId()));
            // if (dejaLike) {
            //     post.getLikes().removeIf(l -> l.getUser().getId().equals(userSession.getId()));
            // } else {
            //     post.addLike(new Like(Like.genererId(), LocalDateTime.now(), null, userSession));
            // }
            resp.sendRedirect(req.getContextPath() + "/feed");
        }
    }


    private Post resolvePost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("postId");
        if (idStr == null || idStr.isBlank()) {

            return null;
        }

        try {
            long id = Long.parseLong(idStr);
            Post post = PostRepository.findPostById(id);

            if (post == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post introuvable");
                return null;
            }

            return post;

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant invalide");
            return null;
        }
    }
    private Comment resolveComment (HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("commentId");
        if (idStr == null || idStr.isBlank()) {

            return null;
        }

        try {
            long id = Long.parseLong(idStr);
            Comment comment = PostRepository.findCommentById(id);

            if (comment == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post introuvable");
                return null;
            }

            return comment;

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant invalide");
            return null;
        }
    }


}
