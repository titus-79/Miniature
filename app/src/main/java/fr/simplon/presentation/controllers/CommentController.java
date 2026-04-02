package fr.simplon.presentation.controllers;

import fr.simplon.application.services.PostService;
import fr.simplon.domain.entities.Comment;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/post")
public class CommentController extends HttpServlet {
    private PostService  postService;


    @Override
    public void init () throws ServletException {
       this.postService = (PostService) getServletContext().getAttribute("postService");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Post post = resolvePost(req, resp);
        if (post == null)
            return;

        User userSession = (User) req.getSession().getAttribute("user");

        req.setAttribute("post", post);
        req.setAttribute("userSession", userSession);
        req.setAttribute("topComments", post.getTopLevelCommentsSortedByDate());

        req.getRequestDispatcher("/WEB-INF/post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        Post post = resolvePost(req, resp);
        if (post == null)
            return;

        String content = req.getParameter("content");
        if (content == null || content.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/post?id=" + post.getId());
            return;
        }

        String parentIdStr = req.getParameter("parentCommentId");
        Comment parentComment = null;

        if (parentIdStr != null && !parentIdStr.isBlank()) {
            try {
                long parentId = Long.parseLong(parentIdStr);
                parentComment = post.getComments().stream()
                        .filter(c -> c.getId().equals(parentId))
                        .findFirst()
                        .orElse(null);
            } catch (NumberFormatException e) {
                System.err.println("parentCommentId invalide : " + parentIdStr);
            }
        }

        Comment newComment = new Comment(
                Comment.genererID(),
                content,
                LocalDateTime.now(),
                userSession,
                post,
                parentComment);

        post.addComment(newComment);

        if (parentComment != null) {
            parentComment.addReply(newComment);
        }

        resp.sendRedirect(req.getContextPath() + "/post?id=" + post.getId());
    }

    private Post resolvePost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return null;
        }

        try {
            long id = Long.parseLong(idStr);
            Optional<Post> post = postService.getPost(id);

            if (post.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Post introuvable");
                return null;
            }

            return post.get();

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Identifiant invalide");
            return null;
        }
    }
}
