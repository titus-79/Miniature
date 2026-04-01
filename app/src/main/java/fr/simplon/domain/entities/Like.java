package fr.simplon.domain.entities;

import java.time.LocalDateTime;

public class Like {
    
    private Long id;
    private LocalDateTime createdAt;
    private User user;
    private Post post;
    private Comment comment;

    public Like(Long id, LocalDateTime createdAt, Post post, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.post = post;
        this.user = user;
    }

    public Like(Comment comment, User user, LocalDateTime createdAt, Long id) {
        this.comment = comment;
        this.user = user;
        this.createdAt = createdAt;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
