package fr.simplon.application.dto;

import fr.simplon.domain.entities.Comment;

public class CommentMapper {
    public static CommentDTO toDTO(Comment comment) {
        UserDTO userDTO = UserMapper.toDTO(comment.getUser());
        CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getComment(),comment.getCreateAt(), userDTO);
        comment.getReplies().forEach(r -> commentDTO.getReplies().add(toDTO(r)));
        return commentDTO;
    }
}
