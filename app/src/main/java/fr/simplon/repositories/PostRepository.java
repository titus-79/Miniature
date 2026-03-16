package fr.simplon.repositories;

import fr.simplon.models.Comment;
import fr.simplon.models.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    // ── Posts ────────────────────────────────────────────────────────────────

    private static final Post POST_TOMCAT = new Post(Post.genererID(),
            "Tomcat vs Jetty, vous utilisez quoi ?",
            "Je commence un nouveau projet et j'hésite entre les deux. Tomcat semble plus répandu mais Jetty a l'air plus léger. Des retours d'expérience ?",
            LocalDateTime.now().minusMinutes(20), false, UserRepository.ALICE);

    private static final Post POST_SERVLET = new Post(Post.genererID(),
            "Enfin compris les Servlets !",
            "Ça fait 3 jours que je bloque sur le cycle de vie doGet/doPost. Ce matin j'ai eu le déclic. Le forward reste côté serveur, le redirect repart du client. Simple mais tout change.",
            LocalDateTime.now().minusHours(1), false, UserRepository.BOB);

    private static final Post POST_HIBERNATE = new Post(Post.genererID(),
            "Hibernate ou JDBC pur ?",
            "Pour un projet de taille moyenne, vaut-il mieux partir sur Hibernate avec toute sa magie ou rester sur du JDBC pour garder le contrôle total des requêtes SQL ?",
            LocalDateTime.now().minusHours(2), false, UserRepository.LEON);

    private static final Post POST_NPE = new Post(Post.genererID(),
            "NullPointerException en JSP",
            "Petite astuce : quand vous avez un null en JSP, vérifiez TOUJOURS que vous passez par le servlet. Aller directement sur le .jsp bypasse tous vos attributs. Perdu 2h là-dessus.",
            LocalDateTime.now().minusHours(4), false, UserRepository.CAMILLE);

    private static final Post POST_SESSION = new Post(Post.genererID(),
            "La gestion des sessions en Java",
            "HttpSession est vraiment puissant. On peut y stocker n'importe quel objet Java. Par contre attention à l'invalidation — pensez toujours à gérer la déconnexion proprement.",
            LocalDateTime.now().minusHours(6), false, UserRepository.HARRY);

    private static final Post POST_MAVEN = new Post(Post.genererID(),
            "Maven vs Gradle ?",
            "Je migre un vieux projet Maven vers Gradle. La syntaxe Groovy est plus concise mais le XML Maven reste plus lisible pour les débutants selon moi.",
            LocalDateTime.now().minusHours(9), false, UserRepository.ALICE);

    private static final Post POST_BCRYPT = new Post(Post.genererID(),
            "BCrypt pour les mots de passe",
            "Si vous stockez encore des mots de passe en clair en 2024, stop. BCrypt avec jbcrypt c'est 2 lignes de code et vos utilisateurs sont protégés. Aucune excuse.",
            LocalDateTime.now().minusHours(11), false, UserRepository.BOB);

    private static final Post POST_MVC = new Post(Post.genererID(),
            "Pattern MVC en pratique",
            "Après avoir tout mis dans les JSP pendant des semaines, j'ai enfin découpé en Model / View / Controller. Le code est tellement plus lisible. Pourquoi j'ai pas fait ça dès le début.",
            LocalDateTime.now().minusHours(14), false, UserRepository.LEON);

    private static final Post POST_404 = new Post(Post.genererID(),
            "Erreur 404 sur Tomcat",
            "Rappel : une 404 sur Tomcat peut venir de l'annotation @WebServlet mal écrite, d'un mauvais chemin dans le dispatcher, ou du web.xml manquant. Checklist à garder sous la main.",
            LocalDateTime.now().minusDays(1), false, UserRepository.CAMILLE);

    private static final Post POST_RECORDS = new Post(Post.genererID(),
            "Java 21 et les records",
            "Vous avez essayé les Records Java ? Pour des objets immuables comme des DTOs c'est parfait. Fini les classes avec 50 lignes de getters/setters pour 3 champs.",
            LocalDateTime.now().minusDays(1).minusHours(3), false, UserRepository.HARRY);

    public static final List<Post> TOUS = new ArrayList<>(List.of(
            POST_TOMCAT, POST_SERVLET, POST_HIBERNATE, POST_NPE, POST_SESSION,
            POST_MAVEN, POST_BCRYPT, POST_MVC, POST_404, POST_RECORDS));

    // ── Seed commentaires ────────────────────────────────────────────────────

    static {

        // ════════════════════════════════════════════════════════════
        // POST : "Tomcat vs Jetty, vous utilisez quoi ?"
        // ════════════════════════════════════════════════════════════

        Comment c1 = new Comment(Comment.genererID(),
                "Tomcat de loin. L'écosystème est bien plus mature et la doc est immense. Pour un projet d'entreprise c'est LE choix par défaut.",
                LocalDateTime.now().minusMinutes(15), UserRepository.BOB, POST_TOMCAT, null);

        Comment c1r1 = new Comment(Comment.genererID(),
                "Complètement d'accord. Et l'intégration avec Spring Boot est quasi transparente depuis quelques versions.",
                LocalDateTime.now().minusMinutes(10), UserRepository.CAMILLE, POST_TOMCAT, c1);

        Comment c1r2 = new Comment(Comment.genererID(),
                "Ouais mais justement, si t'es pas sur Spring Boot, Jetty se configure beaucoup plus vite à l'embarquement.",
                LocalDateTime.now().minusMinutes(5), UserRepository.LEON, POST_TOMCAT, c1);

        Comment c2 = new Comment(Comment.genererID(),
                "Jetty est vraiment top pour les microservices légers ou les tests d'intégration. Pour du Servlet classique en cours, Tomcat reste plus simple à prendre en main.",
                LocalDateTime.now().minusMinutes(8), UserRepository.HARRY, POST_TOMCAT, null);

        Comment c2r1 = new Comment(Comment.genererID(),
                "Les tests c'est un bon point ! On peut démarrer Jetty programmatiquement en 5 lignes dans un JUnit, c'est pratique.",
                LocalDateTime.now().minusMinutes(3), UserRepository.BOB, POST_TOMCAT, c2);

        seedComments(POST_TOMCAT, c1, c1r1, c1r2, c2, c2r1);

        // ════════════════════════════════════════════════════════════
        // POST : "Enfin compris les Servlets !"
        // ════════════════════════════════════════════════════════════

        Comment c3 = new Comment(Comment.genererID(),
                "La distinction forward/redirect c'est vraiment le premier grand déclic en Servlet. Bravo, ça prend parfois beaucoup plus que 3 jours !",
                LocalDateTime.now().minusMinutes(50), UserRepository.CAMILLE, POST_SERVLET, null);

        Comment c3r1 = new Comment(Comment.genererID(),
                "Pour moi ça a cliqué quand j'ai regardé les requêtes dans l'onglet Réseau des DevTools. On voit littéralement la différence : le forward n'apparaît pas côté client.",
                LocalDateTime.now().minusMinutes(40), UserRepository.ALICE, POST_SERVLET, c3);

        Comment c3r2 = new Comment(Comment.genererID(),
                "Exactement ! Le 302 dans la réponse HTTP c'est le signe que c'est un redirect. C'est tellement plus clair une fois visualisé.",
                LocalDateTime.now().minusMinutes(30), UserRepository.HARRY, POST_SERVLET, c3);

        Comment c4 = new Comment(Comment.genererID(),
                "Prochain niveau : comprendre la différence entre les attributs de request, session et application. C'est là où beaucoup perdent des données sans comprendre pourquoi.",
                LocalDateTime.now().minusMinutes(25), UserRepository.LEON, POST_SERVLET, null);

        seedComments(POST_SERVLET, c3, c3r1, c3r2, c4);

        // ════════════════════════════════════════════════════════════
        // POST : "Hibernate ou JDBC pur ?"
        // ════════════════════════════════════════════════════════════

        Comment c5 = new Comment(Comment.genererID(),
                "JDBC pour apprendre, Hibernate pour produire. Si tu maîtrises pas le SQL derrière l'ORM tu vas générer des requêtes catastrophiques sans t'en rendre compte.",
                LocalDateTime.now().minusHours(1).minusMinutes(45), UserRepository.ALICE, POST_HIBERNATE, null);

        Comment c5r1 = new Comment(Comment.genererID(),
                "Le problème N+1 d'Hibernate c'est exactement ça. En production on se retrouve avec 500 requêtes là où 1 jointure suffisait.",
                LocalDateTime.now().minusHours(1).minusMinutes(30), UserRepository.BOB, POST_HIBERNATE, c5);

        Comment c5r2 = new Comment(Comment.genererID(),
                "FetchType.LAZY vs EAGER, le nombre de fois où j'ai explosé des perfs sans comprendre pourquoi... Bien voir les logs SQL en dev, c'est indispensable.",
                LocalDateTime.now().minusHours(1).minusMinutes(15), UserRepository.CAMILLE, POST_HIBERNATE, c5);

        Comment c6 = new Comment(Comment.genererID(),
                "Pour un projet de cours ou MVP, JDBC pur + un bon DAO pattern c'est parfaitement suffisant et ça vous force à écrire du vrai SQL.",
                LocalDateTime.now().minusHours(1), UserRepository.HARRY, POST_HIBERNATE, null);

        seedComments(POST_HIBERNATE, c5, c5r1, c5r2, c6);

        // ════════════════════════════════════════════════════════════
        // POST : "La gestion des sessions en Java"
        // ════════════════════════════════════════════════════════════

        Comment c7 = new Comment(Comment.genererID(),
                "Pensez aussi à setMaxInactiveInterval() pour limiter la durée de vie de la session côté serveur. Par défaut Tomcat c'est 30 min, souvent trop long.",
                LocalDateTime.now().minusHours(5), UserRepository.LEON, POST_SESSION, null);

        Comment c7r1 = new Comment(Comment.genererID(),
                "Bonne pratique ! Et côté sécurité il faut renouveler l'ID de session après la connexion pour éviter les attaques de fixation de session.",
                LocalDateTime.now().minusHours(4).minusMinutes(30), UserRepository.ALICE, POST_SESSION, c7);

        Comment c8 = new Comment(Comment.genererID(),
                "Attention aussi à ne pas stocker trop d'objets lourds en session sur un serveur multi-utilisateurs. Ça consomme de la RAM pour chaque utilisateur connecté simultanément.",
                LocalDateTime.now().minusHours(4), UserRepository.BOB, POST_SESSION, null);

        seedComments(POST_SESSION, c7, c7r1, c8);

        // ════════════════════════════════════════════════════════════
        // POST : "BCrypt pour les mots de passe"
        // ════════════════════════════════════════════════════════════

        Comment c9 = new Comment(Comment.genererID(),
                "100% d'accord. Et le salt est inclus dans le hash retourné par BCrypt, pas besoin de le stocker séparément, c'est ce qui le rend si simple à utiliser.",
                LocalDateTime.now().minusHours(10), UserRepository.CAMILLE, POST_BCRYPT, null);

        Comment c9r1 = new Comment(Comment.genererID(),
                "Exactement. Et le coût (cost factor) est configurable, ce qui permet de ralentir les attaques brute-force même si le matériel évolue.",
                LocalDateTime.now().minusHours(9).minusMinutes(45), UserRepository.HARRY, POST_BCRYPT, c9);

        Comment c9r2 = new Comment(Comment.genererID(),
                "Pour ceux qui débutent : BCrypt.checkpw(motDePasseBrut, hash) retourne un boolean. C'est vraiment 2 lignes comme il dit.",
                LocalDateTime.now().minusHours(9).minusMinutes(20), UserRepository.LEON, POST_BCRYPT, c9);

        Comment c10 = new Comment(Comment.genererID(),
                "Argon2 est encore plus recommandé aujourd'hui par l'OWASP, mais BCrypt reste un excellent choix accessible pour des projets Java avec jbcrypt.",
                LocalDateTime.now().minusHours(9), UserRepository.ALICE, POST_BCRYPT, null);

        seedComments(POST_BCRYPT, c9, c9r1, c9r2, c10);

        // ════════════════════════════════════════════════════════════
        // POST : "Pattern MVC en pratique"
        // ════════════════════════════════════════════════════════════

        Comment c11 = new Comment(Comment.genererID(),
                "La prochaine étape c'est de sortir la logique métier des Servlets dans des classes Service. Le Controller ne devrait appeler que des services, pas manipuler les données directement.",
                LocalDateTime.now().minusHours(13), UserRepository.HARRY, POST_MVC, null);

        Comment c11r1 = new Comment(Comment.genererID(),
                "Oui ! Controller → Service → Repository. Chaque couche a sa responsabilité. Ça facilite aussi les tests unitaires de chaque couche indépendamment.",
                LocalDateTime.now().minusHours(12).minusMinutes(30), UserRepository.BOB, POST_MVC, c11);

        Comment c12 = new Comment(Comment.genererID(),
                "C'est souvent en refactorisant qu'on comprend vraiment le pattern. Partir propre dès le début est difficile sans en avoir vu les bénéfices au moins une fois.",
                LocalDateTime.now().minusHours(12), UserRepository.CAMILLE, POST_MVC, null);

        seedComments(POST_MVC, c11, c11r1, c12);

        // ════════════════════════════════════════════════════════════
        // POST : "Java 21 et les records"
        // ════════════════════════════════════════════════════════════

        Comment c13 = new Comment(Comment.genererID(),
                "Les records combinés avec le pattern matching de Java 21 c'est une vraie révolution. switch(obj) { case MonRecord(var x, var y) -> ... } c'est magnifique.",
                LocalDateTime.now().minusDays(1).minusMinutes(30), UserRepository.ALICE, POST_RECORDS, null);

        Comment c13r1 = new Comment(Comment.genererID(),
                "Les sealed classes avec ça c'est encore mieux. Tu modélises des ADT comme en Scala ou Haskell, mais en Java.",
                LocalDateTime.now().minusDays(1).minusMinutes(15), UserRepository.LEON, POST_RECORDS, c13);

        Comment c14 = new Comment(Comment.genererID(),
                "Attention quand même, les Records ne remplacent pas tout. Dès qu'on a besoin de mutabilité ou d'héritage, on reste sur des classes classiques.",
                LocalDateTime.now().minusDays(1), UserRepository.BOB, POST_RECORDS, null);

        seedComments(POST_RECORDS, c13, c13r1, c14);
    }

    

    // ── Utilitaire seed ──────────────────────────────────────────────────────

    /**
     * Attache les commentaires au post ET lie chaque réponse à son parent.
     * Les parents doivent être passés avant leurs réponses.
     */
    private static void seedComments(Post post, Comment... comments) {
        for (Comment c : comments) {
            post.addComment(c);
            if (c.getParentComment() != null) {
                c.getParentComment().addReply(c);
            }
        }
    }

    // ── Recherche ────────────────────────────────────────────────────────────

    public static Post findById(Long id) {
        return TOUS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}