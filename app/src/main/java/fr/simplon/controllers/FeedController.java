package fr.simplon.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.simplon.models.Post;
import fr.simplon.models.User;
import fr.simplon.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")

public class FeedController extends HttpServlet {
    private final List<Post> posts = new ArrayList<>(List.of(

            new Post(Post.genererID(),
                    "Tomcat vs Jetty, vous utilisez quoi ?",
                    "Je commence un nouveau projet et j'hésite entre les deux. Tomcat semble plus répandu mais Jetty a l'air plus léger. Des retours d'expérience ?",
                    LocalDateTime.now().minusMinutes(20), false, UserRepository.ALICE),

            new Post(Post.genererID(),
                    "Enfin compris les Servlets !",
                    "Ça fait 3 jours que je bloque sur le cycle de vie doGet/doPost. Ce matin j'ai eu le déclic. Le forward reste côté serveur, le redirect repart du client. Simple mais tout change.",
                    LocalDateTime.now().minusHours(1), false, UserRepository.BOB),

            new Post(Post.genererID(),
                    "Hibernate ou JDBC pur ?",
                    "Pour un projet de taille moyenne, vaut-il mieux partir sur Hibernate avec toute sa magie ou rester sur du JDBC pour garder le contrôle total des requêtes SQL ?",
                    LocalDateTime.now().minusHours(2), false, UserRepository.LEON),

            new Post(Post.genererID(),
                    "NullPointerException en JSP",
                    "Petite astuce : quand vous avez un null en JSP, vérifiez TOUJOURS que vous passez par le servlet. Aller directement sur le .jsp bypasse tous vos attributs. Perdu 2h là-dessus.",
                    LocalDateTime.now().minusHours(4), false, UserRepository.CAMILLE),

            new Post(Post.genererID(),
                    "La gestion des sessions en Java",
                    "HttpSession est vraiment puissant. On peut y stocker n'importe quel objet Java. Par contre attention à l'invalidation — pensez toujours à gérer la déconnexion proprement.",
                    LocalDateTime.now().minusHours(6), false, UserRepository.HARRY),

            new Post(Post.genererID(),
                    "Maven vs Gradle ?",
                    "Je migre un vieux projet Maven vers Gradle. La syntaxe Groovy est plus concise mais le XML Maven reste plus lisible pour les débutants selon moi.",
                    LocalDateTime.now().minusHours(9), false, UserRepository.ALICE),

            new Post(Post.genererID(),
                    "BCrypt pour les mots de passe",
                    "Si vous stockez encore des mots de passe en clair en 2024, stop. BCrypt avec jbcrypt c'est 2 lignes de code et vos utilisateurs sont protégés. Aucune excuse.",
                    LocalDateTime.now().minusHours(11), false, UserRepository.BOB),

            new Post(Post.genererID(),
                    "Pattern MVC en pratique",
                    "Après avoir tout mis dans les JSP pendant des semaines, j'ai enfin découpé en Model / View / Controller. Le code est tellement plus lisible. Pourquoi j'ai pas fait ça dès le début.",
                    LocalDateTime.now().minusHours(14), false, UserRepository.LEON),

            new Post(Post.genererID(),
                    "Erreur 404 sur Tomcat",
                    "Rappel : une 404 sur Tomcat peut venir de l'annotation @WebServlet mal écrite, d'un mauvais chemin dans le dispatcher, ou du web.xml manquant. Checklist à garder sous la main.",
                    LocalDateTime.now().minusDays(1), false, UserRepository.CAMILLE),

            new Post(Post.genererID(),
                    "Java 21 et les records",
                    "Vous avez essayé les Records Java ? Pour des objets immuables comme des DTOs c'est parfait. Fini les classes avec 50 lignes de getters/setters pour 3 champs.",
                    LocalDateTime.now().minusDays(1).minusHours(3), false, UserRepository.HARRY)));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userSession = (User) req.getSession().getAttribute("user");
        req.setAttribute("userSession", userSession);

        String type = req.getParameter("type");
        if (type == null || type.isBlank()) {
            type = "recommande";
        }
        if (type.equals("abonne")) {
            req.setAttribute("feedTitre", "Fil abonnements");
            req.setAttribute("feedVide", "Vous n'avez pas encore d'abonnements.");
        } else {
            req.setAttribute("feedTitre", "Fil recommandations");
            req.setAttribute("feedVide", "Aucune recommandation pour le moment.");
        }

        List<Post> postsTries = new ArrayList<>(posts);
        postsTries.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        req.setAttribute("posts", postsTries);
        req.setAttribute("typeActif", type);
        req.setAttribute("feedTitre", type.equals("abonne") ? "Fil abonnements" : "Fil recommandations");

        req.getRequestDispatcher("/feed.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userSession = (User) req.getSession().getAttribute("user");
        if (userSession == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        boolean champsValides = title != null && !title.isBlank()
                && content != null && !content.isBlank();

        if (champsValides) {
            posts.add(new Post(Post.genererID(), title, content, LocalDateTime.now(), false, userSession));
            resp.sendRedirect("/feed");
        } else {
            resp.sendRedirect("/feed");
        }
    }
}
