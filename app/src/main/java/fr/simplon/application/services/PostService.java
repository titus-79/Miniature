package fr.simplon.application.services;

import fr.simplon.domain.entities.Post;
import fr.simplon.domain.repository.IPostRepository;

import java.util.Optional;


public class PostService {
    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);

    }

}
