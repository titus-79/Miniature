package fr.simplon.models;

import java.time.LocalDateTime;

public class Like {
    
    private Long id;
    private LocalDateTime createdAt;
    private User user;
    private Post post;

    public Like(Long id, LocalDateTime createdAt, Post post, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.post = post;
        this.user = user;
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
    

}
