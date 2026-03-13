package fr.simplon.controllers;

import fr.simplon.models.User;
import fr.simplon.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/follow")
public class FollowController extends HttpServlet {

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
                long targetId = Long.parseLong(targetIdStr);

                User target = UserRepository.TOUS.stream()
                        .filter(u -> u.getId() == targetId)
                        .findFirst()
                        .orElse(null);

                if (target != null) {
                    if ("follow".equals(action)) {
                        currentUser.follow(target);
                        System.out.println(currentUser.getUserName()
                                + " suit maintenant " + target.getUserName());
                    } else if ("unfollow".equals(action)) {
                        currentUser.unfollow(target);
                        System.out.println(currentUser.getUserName()
                                + " ne suit plus " + target.getUserName());
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("targetId invalide : " + targetIdStr);
            }
        }

        resp.sendRedirect(req.getContextPath() + redirectUrl);
    }
}