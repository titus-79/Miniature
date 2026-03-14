package fr.simplon.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean isDraft;
    private User author;
    private final List<Comment> comments = new ArrayList<>();
    private final List<Like> likes = new ArrayList<>();
    private static Long compteur = 0L;

    public Post(Long id, String title, String content,
            LocalDateTime createdAt, boolean isDraft, User author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.isDraft = isDraft;
        this.author = author;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getCommentsSortedByDate() {
        return comments.stream()
                .sorted((a, b) -> b.getCreateAt().compareTo(a.getCreateAt()))
                .toList();
    }

    public List<Comment> getTopLevelCommentsSortedByDate() {
        return comments.stream()
                .filter(Comment::isTopLevel)
                .sorted((a, b) -> b.getCreateAt().compareTo(a.getCreateAt()))
                .toList();
    }

    public int getCommentCount() {
        return comments.size();
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public int getLikeCount() {
        return likes.size();
    }

    public static long genererID() {
        return compteur++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setCreatedAt(LocalDateTime t) {
        this.createdAt = t;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Like> getLikes() {
        return likes;
    }
}
