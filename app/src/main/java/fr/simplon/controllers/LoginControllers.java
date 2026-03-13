package fr.simplon.controllers;

import fr.simplon.models.User;
import fr.simplon.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")

public class LoginControllers extends HttpServlet {

    private static final List<User> users = UserRepository.TOUS;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User found = null;
        for (User u : users) {

            if (u.getUserName().equals(login) && BCrypt.checkpw(password, u.getPasswordHash())) {
                found = u;
                break;
            }
        }
        if (found != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", found);
            System.out.println("connecter : " + found.getUserName());
            resp.sendRedirect(req.getContextPath() + "/feed");
        } else {
            System.out.println("Echec de connection :" + login);
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
