package fr.simplon.models;

import java.time.LocalDateTime;

public class Post {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean isDraft;
    private static Long compteur = 0L;
    private User author;

    public Post(Long id, String title, String content, LocalDateTime createdAt, boolean isDraft, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.isDraft = isDraft;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    public void addComment(Comment comment) {
        // TODO
    }

    public void addLike(Like like) {
        // TODO
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    // public List<Comment> getCommentSortedByDate() {
    // //TODO
    // }

    public static long genererID() {
        return compteur++;
    }
}
