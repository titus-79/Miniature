package fr.simplon.domain.repository;

import fr.simplon.domain.entities.Comment;
import fr.simplon.domain.entities.Post;
 
import java.util.List;
import java.util.Optional;

public interface IPostRepository {
 
    List<Post> findAll();
 
    Optional<Post> findById(Long id);
 
    Post save(Post post);
 
    Optional<Comment> findCommentById(Long id);
}
