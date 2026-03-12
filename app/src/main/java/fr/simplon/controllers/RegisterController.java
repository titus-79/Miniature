package fr.simplon.controllers;

import java.io.IOException;
import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;

import fr.simplon.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")

public class RegisterController extends HttpServlet {

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
            String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

            User newUser = new User(
                    User.genererID(),
                    login,
                    email,
                    passwordHash,
                    LocalDateTime.now());

            HttpSession session = req.getSession();
            session.setAttribute("user", newUser);

            System.out.println("valider :" + newUser);
            resp.sendRedirect("/feed");

        } else {
            System.out.println("Champs invalides ou mots de passe différents");
            resp.sendRedirect("/register");
        }

    }
}
