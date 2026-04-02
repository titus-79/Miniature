package fr.simplon.application.services;

import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;
 
import java.time.LocalDateTime;
import java.util.Optional;

public class AuthService {

    private final IUserRepository userRepository;
    
    public AuthService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(u -> BCrypt.checkpw(password, u.getPasswordHash()));
    }

    public User register(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà pris.");
        }
        User user = new User(User.genererID(), username, email,
                             BCrypt.hashpw(password, BCrypt.gensalt()),
                             LocalDateTime.now());
        return userRepository.save(user);
    }
}