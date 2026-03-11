package fr.simplon.models;

import java.time.LocalDateTime;

public class Comment {

    private String comment;
    private Long id;
    private LocalDateTime createAt;
    private User user;

    public Comment(String comment, Long id, LocalDateTime createAt, User user) {
        this.comment = comment;
        this.id = id;
        this.createAt = createAt;
        this.user = user;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
