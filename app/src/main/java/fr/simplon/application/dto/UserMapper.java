package fr.simplon.application.dto;

import fr.simplon.domain.entities.User;

public class UserMapper {
    public static UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO(user.getId(), user.getUserName(), user.getEmail());
        user.getFollowing().forEach(f -> userDTO.getFollowing().add(toDTO(f)));
       return userDTO;
    }

}
