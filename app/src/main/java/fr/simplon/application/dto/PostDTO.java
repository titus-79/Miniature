package fr.simplon.application.dto;



public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private UserDTO author;
   // private final List<CommentDTO> comments = new ArrayList<>();
   // private final List<LikeDTO> likes = new ArrayList<>();


    public PostDTO(Long id, String title, String content, UserDTO author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
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
}
