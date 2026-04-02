package fr.simplon.presentation.controllers;

import java.io.IOException;

import fr.simplon.application.services.PostService;
import fr.simplon.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/post/create")
public class PostController extends HttpServlet{
    private PostService postService;

    @Override
    public void init() {
        this.postService = (PostService) getServletContext().getAttribute("postService");
    }
    
     @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String title = req.getParameter("title");
        String content = req.getParameter("content");

        if (title != null && !title.isBlank() && content != null && !content.isBlank()) {
            postService.createPost(title, content, userSession);
        }

        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}
