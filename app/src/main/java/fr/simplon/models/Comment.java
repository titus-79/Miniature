package fr.simplon.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Comment {

    private Long id;
    private String comment;
    private LocalDateTime createAt;
    private User user;
    private Post post;
    private Comment parentComment;
    private final List<Comment> replies = new ArrayList<>();
    private static long compteur = 0L;
    private final List<Like> likes = new ArrayList<>();

    public Comment(Long id, String comment, LocalDateTime createAt,
            User user, Post post, Comment parentComment) {
        this.id = id;
        this.comment = comment;
        this.createAt = createAt;
        this.user = user;
        this.post = post;
        this.parentComment = parentComment;
    }

    public void addReply(Comment reply) {
        replies.add(reply);
    }

    public List<Comment> getRepliesSortedByDate() {
        return replies.stream()
                .sorted((a, b) -> b.getCreateAt().compareTo(a.getCreateAt()))
                .toList();
    }


    public boolean isTopLevel() {
        return parentComment == null;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime t) {
        this.createAt = t;
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

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parent) {
        this.parentComment = parent;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void addLike(Like like) {
        likes.add(like);
    }
    public int getLikesCount(){
        return this.likes.size();
    }
    public void removeLike(Like like) {
        likes.remove(like);
    }
    public List<Like> getLikes() {
        return likes;
    }

}
