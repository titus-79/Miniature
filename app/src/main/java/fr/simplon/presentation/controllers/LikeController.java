package fr.simplon.presentation.controllers;

import fr.simplon.application.useCases.LikePostUseCase;
import fr.simplon.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/like")
public class LikeController extends HttpServlet {

    private LikePostUseCase likePostUseCase;

    @Override
    public void init() {
        this.likePostUseCase = (LikePostUseCase) getServletContext().getAttribute("likeUseCase");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String postIdStr = req.getParameter("postId");
        String commentIdStr = req.getParameter("commentId");

        try {
            if (commentIdStr != null && !commentIdStr.isBlank()) {
                likePostUseCase.toggleCommentLike(Long.parseLong(commentIdStr), userSession);
                resp.sendRedirect(req.getContextPath() + "/post?id=" + req.getParameter("postId"));
                return;
            }
            if (postIdStr != null && !postIdStr.isBlank()) {
                likePostUseCase.togglePostLike(Long.parseLong(postIdStr), userSession);
                resp.sendRedirect(req.getContextPath() + "/feed");
                return;
            }
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

}
