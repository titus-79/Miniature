package fr.simplon.application.dto;

import fr.simplon.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private final List<UserDTO> following = new ArrayList<>();


    public UserDTO(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<UserDTO> getFollowing() {
        return following;
    }
}
