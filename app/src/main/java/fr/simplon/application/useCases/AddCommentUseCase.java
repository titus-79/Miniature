package fr.simplon.application.useCases;

import java.time.LocalDateTime;

import fr.simplon.domain.entities.Comment;
import fr.simplon.domain.entities.Post;
import fr.simplon.domain.entities.User;
import fr.simplon.domain.repository.IPostRepository;

public class AddCommentUseCase {

    private final IPostRepository postRepository;

    public AddCommentUseCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Comment execute(Post post, String content, User author, Long parentCommentId) {
        Comment parentComment = null;

        if (parentCommentId != null) {
            parentComment = post.getComments().stream()
                    .filter(c -> c.getId().equals(parentCommentId))
                    .findFirst()
                    .orElse(null);
        }

        Comment newComment = new Comment(
                null,
                content,
                LocalDateTime.now(),
                author,
                post,
                parentComment);

        postRepository.saveComment(newComment);
        post.addComment(newComment);

        if (parentComment != null) {
            parentComment.addReply(newComment);
        }

        return newComment;
    }
}
