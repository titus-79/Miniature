package fr.simplon.application.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDTO {

    private Long id;
    private String comment;
    private LocalDateTime createAt;
    private UserDTO user;
    private final List<CommentDTO> replies = new ArrayList<>();
    private final List<LikeDTO> likes = new ArrayList<>();

    public CommentDTO(Long id, String comment, LocalDateTime createAt, UserDTO user) {
        this.id = id;
        this.comment = comment;
        this.createAt = createAt;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public List<CommentDTO> getReplies() {
        return replies;
    }

    public List<LikeDTO> getLikes() {
        return likes;
    }
}
