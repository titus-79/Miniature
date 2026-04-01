package fr.simplon.application.useCases;

import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IUserRepository;

public class FollowUserUseCase {

    private final IUserRepository userRepository;

    public FollowUserUseCase(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void toggleUserFollow(Long targetId, User currentUser, String action) {
        User target = userRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable : " + targetId));

        if ("follow".equals(action)) {
            currentUser.follow(target);
            System.out.println(currentUser.getUserName()
                    + " suit maintenant " + target.getUserName());
        } else if ("unfollow".equals(action)) {
            currentUser.unfollow(target);
            System.out.println(currentUser.getUserName()
                    + " ne suit plus " + target.getUserName());
        }
    }
}
