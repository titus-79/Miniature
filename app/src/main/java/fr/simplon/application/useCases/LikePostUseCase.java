package fr.simplon.application.useCases;

import java.time.LocalDateTime;

import fr.simplon.domain.entities.Comment;
import fr.simplon.domain.entities.Like;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IPostRepository;

public class LikePostUseCase {

    private final IPostRepository postRepository;

    public LikePostUseCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void togglePostLike(Long postId, User user) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post introuvable : " + postId));

        boolean dejaLike = post.getLikes().stream()
                .anyMatch(l -> l.getUser().getId().equals(user.getId()));

        if (dejaLike) {
            post.getLikes().removeIf(l -> l.getUser().getId().equals(user.getId()));
        } else {
            Like like = postRepository.saveLike(new Like(null, LocalDateTime.now(), null, user));
            post.addLike(like);
        }
    }

    public void toggleCommentLike(Long commentId, User user) {
        Comment comment = postRepository.findCommentById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Commentaire introuvable : " + commentId));

        boolean dejaLike = comment.getLikes().stream()
                .anyMatch(l -> l.getUser().getId().equals(user.getId()));

        if (dejaLike) {
            comment.getLikes().removeIf(l -> l.getUser().getId().equals(user.getId()));
        } else {
            Like like = postRepository.saveLike(new Like(null, LocalDateTime.now(), null, user));
            comment.addLike(like);
        }
    }

}
