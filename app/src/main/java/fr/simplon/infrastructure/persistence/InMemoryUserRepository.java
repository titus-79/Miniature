package fr.simplon.infrastructure.persistence;

import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements IUserRepository {

    private final List<User> store = new ArrayList<>();

    private long nextId = 0L;

    private long nextId() {
        return nextId++;
    }

    public InMemoryUserRepository() {
        seed();
    }

    // ── Interface ────────────────────────────────────────────────────────────

    @Override
    public List<User> findAll() {
        return List.copyOf(store);
    }

    @Override
    public Optional<User> findById(Long id) {
        return store.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return store.stream().filter(u -> u.getUserName().equals(username)).findFirst();
    }

    @Override
    public User save(User user) {
        user.setId(nextId());
        store.add(user);
        return user;
    }

    // ── Données initiales ────────────────────────────────────────────────────

    private void seed() {
        save(new User(null, "alice", "alice@mail.com", BCrypt.hashpw("password123", BCrypt.gensalt()),
                LocalDateTime.now()));
        save(new User(null, "bob", "bob@mail.com", BCrypt.hashpw("password123", BCrypt.gensalt()),
                LocalDateTime.now()));
        save(new User(null, "harry", "harry@mail.com", BCrypt.hashpw("password123", BCrypt.gensalt()),
                LocalDateTime.now()));
        save(new User(null, "camille", "camille@mail.com", BCrypt.hashpw("password123", BCrypt.gensalt()),
                LocalDateTime.now()));
        save(new User(null, "leon", "leon@mail.com", BCrypt.hashpw("password123", BCrypt.gensalt()),
                LocalDateTime.now()));
    }
}
