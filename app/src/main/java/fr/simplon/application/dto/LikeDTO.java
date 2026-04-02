package fr.simplon.application.dto;



public class LikeDTO {

    private Long id;
    private UserDTO user;
    private PostDTO post;


    public LikeDTO(Long id, UserDTO user, PostDTO post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public UserDTO getUser() {
        return user;
    }

    public PostDTO getPost() {
        return post;
    }
}
