package fr.simplon.application.dto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private UserDTO author;
    private final List<CommentDTO> comments = new ArrayList<>();
    private final List<LikeDTO> likes = new ArrayList<>();
    private LocalDateTime createAt;

    public PostDTO(Long id, String title, String content, UserDTO author, LocalDateTime createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createAt = createAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public List<LikeDTO> getLikes() {
        return likes;
    }
}
