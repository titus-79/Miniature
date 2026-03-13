package fr.simplon.repositories;

import java.time.LocalDateTime;
import java.util.List;

import fr.simplon.models.User;

public class UserRepository {
    public static final User ALICE = new User(User.genererID(), "alice", "alice@mail.com", "password123",
            LocalDateTime.now());
    public static final User BOB = new User(User.genererID(), "bob", "bob@mail.com", "password123",
            LocalDateTime.now());
    public static final User HARRY = new User(User.genererID(), "harry", "harry@mail.com", "password123",
            LocalDateTime.now());
    public static final User CAMILLE = new User(User.genererID(), "camille", "camille@mail.com", "password123",
            LocalDateTime.now());
    public static final User LEON = new User(User.genererID(), "leon", "leon@mail.com", "password123",
            LocalDateTime.now());

    public static final List<User> TOUS = List.of(ALICE, BOB, HARRY, CAMILLE, LEON);

}
