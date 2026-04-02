package fr.simplon.application.dto;

import fr.simplon.domain.entities.Like;

public class LikeMapper {
    public static LikeDTO toDTO(Like like) {
        UserDTO userDTO = UserMapper.toDTO(like.getUser());
        PostDTO postDTO = PostMapper.toDTO(like.getPost());
        LikeDTO likeDTO = new LikeDTO(like.getId(), userDTO,postDTO);

        return likeDTO;
    }

}
