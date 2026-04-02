package fr.simplon.presentation.controllers;

import fr.simplon.application.dto.CommentDTO;
import fr.simplon.application.dto.CommentMapper;
import fr.simplon.application.dto.PostDTO;
import fr.simplon.application.dto.PostMapper;
import fr.simplon.application.services.PostService;
import fr.simplon.application.useCases.AddCommentUseCase;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/post")
public class CommentController extends HttpServlet {
    private PostService  postService;
    private AddCommentUseCase addCommentUseCase;


    @Override
    public void init () throws ServletException {
       this.postService = (PostService) getServletContext().getAttribute("postService");
       this.addCommentUseCase = (AddCommentUseCase) getServletContext().getAttribute("addCommentUseCase");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Post post = resolvePost(req, resp);
        if(post == null)
            return;

        User userSession = (User) req.getSession().getAttribute("user");

        PostDTO postDTO = PostMapper.toDTO(post);
        List<CommentDTO> topComments = postService.getTopLevelComments(post)
                .stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());


        req.setAttribute("post", postDTO);
        req.setAttribute("userSession", userSession);
        req.setAttribute("topComments",topComments);

        req.getRequestDispatcher("/WEB-INF/post.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Post post = resolvePost(req, resp);
        if (post == null) return;

        String content = req.getParameter("content");
        if (content == null || content.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/post?id=" + post.getId());
            return;
        }

        String parentIdStr = req.getParameter("parentCommentId");
        Long parentId = null;
        if (parentIdStr != null && !parentIdStr.isBlank()) {
            try { parentId = Long.parseLong(parentIdStr); }
            catch (NumberFormatException ignored) {}
        }

        addCommentUseCase.execute(post, content, userSession, parentId); 

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
