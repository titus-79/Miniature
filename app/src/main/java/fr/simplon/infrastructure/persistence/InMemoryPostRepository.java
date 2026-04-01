package fr.simplon.infrastructure.persistence;

import fr.simplon.domain.entities.Comment;
import fr.simplon.domain.entities.Like;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IPostRepository;
import fr.simplon.domain.repository.IUserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation en mémoire de IPostRepository.
 * Reçoit IUserRepository par injection de dépendances pour récupérer
 * les auteurs du seed — principe D appliqué même ici.
 */
public class InMemoryPostRepository implements IPostRepository {

    private final List<Post> posts = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<Like> likes = new ArrayList<>();

    private long nextId = 0L;

    private long nextId() {
        return nextId++;
    }

    public InMemoryPostRepository(IUserRepository userRepository) {
        seed(userRepository);
    }

    // ── Interface ────────────────────────────────────────────────────────────

    @Override
    public List<Post> findAll() {
        return List.copyOf(posts);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return posts.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Post save(Post post) {
        post.setId(nextId());
        posts.add(post);
        return post;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return comments.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public Comment saveComment(Comment comment) {
        comment.setId(nextId());
        comments.add(comment);
        return comment;
    }

    @Override
    public Like saveLike(Like like) {
        like.setId(nextId());
        likes.add(like);
        return like;
    }

    // ── Seed ─────────────────────────────────────────────────────────────────

    private void seed(IUserRepository users) {
        // On récupère les utilisateurs via l'interface — pas de référence statique
        User alice = users.findByUsername("alice").orElseThrow();
        User bob = users.findByUsername("bob").orElseThrow();
        User harry = users.findByUsername("harry").orElseThrow();
        User camille = users.findByUsername("camille").orElseThrow();
        User leon = users.findByUsername("leon").orElseThrow();

        Post tomcat = post("Tomcat vs Jetty, vous utilisez quoi ?",
                "Je commence un nouveau projet et j'hésite entre les deux.",
                LocalDateTime.now().minusMinutes(20), alice);

        Post servlet = post("Enfin compris les Servlets !",
                "Ça fait 3 jours que je bloque sur le cycle de vie doGet/doPost.",
                LocalDateTime.now().minusHours(1), bob);

        Post hibernate = post("Hibernate ou JDBC pur ?",
                "Pour un projet de taille moyenne, vaut-il mieux partir sur Hibernate ?",
                LocalDateTime.now().minusHours(2), leon);

        Post bcrypt = post("BCrypt pour les mots de passe",
                "Si vous stockez encore des mots de passe en clair en 2024, stop.",
                LocalDateTime.now().minusHours(11), bob);

        Post mvc = post("Pattern MVC en pratique",
                "Après avoir tout mis dans les JSP pendant des semaines, j'ai enfin découpé.",
                LocalDateTime.now().minusHours(14), leon);

        // Commentaires sur Tomcat
        Comment c1 = comment("Tomcat de loin. L'écosystème est bien plus mature.",
                LocalDateTime.now().minusMinutes(15), bob, tomcat, null);
        Comment c1r1 = comment("Complètement d'accord. Et l'intégration Spring Boot est transparente.",
                LocalDateTime.now().minusMinutes(10), camille, tomcat, c1);
        seedComments(tomcat, c1, c1r1);

        // Commentaires sur BCrypt
        Comment c9 = comment("100% d'accord. Le salt est inclus dans le hash retourné.",
                LocalDateTime.now().minusHours(10), camille, bcrypt, null);
        Comment c9r1 = comment("Le coût est configurable, ce qui ralentit les attaques brute-force.",
                LocalDateTime.now().minusHours(9).minusMinutes(45), harry, bcrypt, c9);
        seedComments(bcrypt, c9, c9r1);

        // Commentaires sur MVC
        Comment c11 = comment("La prochaine étape : sortir la logique des Servlets dans des Services.",
                LocalDateTime.now().minusHours(13), harry, mvc, null);
        seedComments(mvc, c11);
    }

    private Post post(String title, String content, LocalDateTime date, User author) {
        return save(new Post(null, title, content, date, false, author));
    }

    private Comment comment(String text, LocalDateTime date, User author, Post post, Comment parent) {
        return saveComment(new Comment(null, text, date, author, post, parent));
    }

    private void seedComments(Post post, Comment... cs) {
        for (Comment c : cs) {
            post.addComment(c);
            if (c.getParentComment() != null) {
                c.getParentComment().addReply(c);
            }
        }
    }
}
