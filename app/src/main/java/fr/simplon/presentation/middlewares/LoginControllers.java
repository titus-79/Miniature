package fr.simplon.presentation.middlewares;

import fr.simplon.application.services.AuthService;
import fr.simplon.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")

public class LoginControllers extends HttpServlet {

    // private static final List<User> users = InMemoryUserRepository.findAll();

    private AuthService authService;

    @Override
    public void init() {
        this.authService = (AuthService) getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/webapp/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Optional<User> found = authService.login(login, password);

        if (found.isPresent()) {
            req.getSession().setAttribute("user", found.get());
            resp.sendRedirect(req.getContextPath() + "/feed");
        } else {
            req.setAttribute("erreur", "Login ou mot de passe incorrect");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
