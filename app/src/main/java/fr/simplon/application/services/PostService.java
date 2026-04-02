package fr.simplon.application.services;

import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IPostRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class PostService {
    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);

    }

    public Post createPost(String title, String content, User author) {
        return postRepository.save(
                new Post(null, title, content,
                        LocalDateTime.now(), false, author));
    }

}
