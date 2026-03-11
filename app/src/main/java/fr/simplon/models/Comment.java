package fr.simplon.models;

import java.time.LocalDateTime;

public class Comment {

    private String comment;
    private Long id;
    private LocalDateTime createAt;
    private Post post;

    public Comment(String comment, Long id, LocalDateTime createAt, Post post) {
        this.comment = comment;
        this.id = id;
        this.createAt = createAt;
        this.post = post;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
