package fr.simplon.presentation.controllers;

import fr.simplon.application.useCases.FollowUserUseCase;
import fr.simplon.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/follow")
public class FollowController extends HttpServlet {

    private FollowUserUseCase followUserUseCase;

    @Override
    public void init() {
        this.followUserUseCase = (FollowUserUseCase) getServletContext().getAttribute("followUseCase");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User currentUser = (User) req.getSession().getAttribute("user");
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String targetIdStr = req.getParameter("targetId");
        String action = req.getParameter("action");
        String redirectUrl = req.getParameter("redirect");
        if (redirectUrl == null || redirectUrl.isBlank()) {
            redirectUrl = "/feed";
        }

        if (targetIdStr != null && action != null) {
            try {
                followUserUseCase.toggleUserFollow(Long.parseLong(targetIdStr), currentUser, action);
            } catch (IllegalArgumentException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                return;
            }
        }

        resp.sendRedirect(req.getContextPath() + redirectUrl);
    }
}