package fr.simplon.models;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean isDraft;
    
    public Post(Long id, String title, String content, LocalDateTime createdAt, boolean isDraft) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.isDraft = isDraft;
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
        //TODO
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // public List<Comment> getCommentSortedByDate() {
    //     //TODO
    // }
}
