package fr.simplon.application.dto;

import fr.simplon.domain.entities.Post;




public class PostMapper {

    public static PostDTO toDTO(Post post) {
        UserDTO userDTO = UserMapper.toDTO(post.getAuthor());
        PostDTO postDTO = new PostDTO(post.getId(), post.getTitle(), post.getContent(), userDTO, post.getCreatedAt());
        post.getLikes().forEach(l -> postDTO.getLikes().add(LikeMapper.toDTO(l)));
        post.getComments().forEach(c -> postDTO.getComments().add(CommentMapper.toDTO(c)));
        return postDTO;
    }
}
