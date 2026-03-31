package fr.simplon.presentation.middlewares;

import java.io.IOException;


import fr.simplon.application.services.AuthService;
import fr.simplon.domain.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")

public class RegisterController extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() {
        this.authService = (AuthService) getServletContext().getAttribute("authService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String passwordConf = req.getParameter("passwordConf");

        boolean champsValides = login != null && !login.isBlank()
                && email != null && !email.isBlank()
                && password != null && !password.isBlank()
                && password.equals(passwordConf);

        if (champsValides) {
            // String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

            // User newUser = new User(
            // User.genererID(),
            // login,
            // email,
            // passwordHash,
            // LocalDateTime.now());
            User newUser = authService.register(login, email, password);

            HttpSession session = req.getSession();
            session.setAttribute("user", newUser);

            System.out.println("valider :" + newUser);
            resp.sendRedirect(req.getContextPath() + "/feed");

        } else {
            req.setAttribute("erreur", "Login ou mot de passe incorrect");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }

    }
}
