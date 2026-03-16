package fr.simplon.controllers;

import fr.simplon.models.Comment;
import fr.simplon.models.Like;
import fr.simplon.models.Post;
import fr.simplon.models.User;
import fr.simplon.repositories.PostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
@WebServlet("/like")
public class LikeController extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        }

        Post post = resolvePost(req, resp);
        if (post == null){
            resp.sendRedirect(req.getContextPath() + "/login");
        }

        Comment comment = resolveComment(req,resp);
       if(comment != null){
          Like like = new Like(comment, userSession, LocalDateTime.now(),Like.genererId());
            comment.addLike(like);
       }

       if(post != null) {
           Like like = new Like(Like.genererId(), LocalDateTime.now(), post, userSession);
           post.addLike(like);
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
