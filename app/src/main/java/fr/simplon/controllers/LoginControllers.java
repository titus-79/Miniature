package fr.simplon.controllers;

import fr.simplon.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class LoginControllers extends HttpServlet {

   private static final List<User> users = List.of(
           new User(1L, "alice", "alice@mail.com", "password123", LocalDateTime.now()),
           new User(2L, "bob", "bob@mail.com", "secret456", LocalDateTime.now()),
           new User(3L, "harry", "harry@mail.com", "admin789", LocalDateTime.now())

   );
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User found = null;
        for(User u : users){


        if (u.getUserName().equals(login)&& u.getPasswordHash().equals(password)){
            found = u;
            break;
        }
        }
        if (found != null){
            HttpSession session = req.getSession();
            session.setAttribute("user", found);
            System.out.println("connecter : "+ found.getUserName());
            resp.sendRedirect(req.getContextPath() +"/feed");
        }else{
            System.out.println("Echec de connection :"+ login);
            resp.sendRedirect(req.getContextPath()+ "/login");
        }




    }






}
