package fr.simplon.application.services;

import java.util.List;
import java.util.stream.Collectors;
import fr.simplon.application.dto.PostDTO;
import fr.simplon.application.dto.PostMapper;
import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IPostRepository;

public class FeedService {

    private final IPostRepository postRepository;

    public FeedService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private List<PostDTO> getPost() {
        return postRepository.findAll().stream()
                .sorted((a, b)-> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }



    public List<PostDTO> getRecommendedFeed() {
        return getPost();
    }

    private List<PostDTO> postUserFollow(List<PostDTO> posts, List<User> following) {
        posts = posts.stream()
                .filter(p -> following.stream()
                        .anyMatch(u -> u.getId().equals(p.getAuthor().getId())))
                .collect(Collectors.toList());
        return posts;
    }

    public List<PostDTO> getFollowingFeed(User userSession) {
        return postUserFollow(getRecommendedFeed(), userSession.getFollowing());
    }

}
