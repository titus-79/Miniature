package fr.simplon.application.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IPostRepository;

public class FeedService {

    private final IPostRepository postRepository;

    public FeedService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private List<Post> getPost() {
        return postRepository.findAll();
    }

    private List<Post> postTries(List<Post> posts) {
        return posts.stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<Post> getRecommendedFeed() {
        return postTries(getPost());
    }

    private List<Post> postUserFollow(List<Post> posts, List<User> following) {
        posts = posts.stream()
                .filter(p -> following.stream()
                        .anyMatch(u -> u.getId().equals(p.getAuthor().getId())))
                .collect(Collectors.toList());
        return posts;
    }

    public List<Post> getFollowingFeed(User userSession) {
        return postUserFollow(getRecommendedFeed(), userSession.getFollowing());
    }

}
