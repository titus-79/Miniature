package fr.simplon.models;

public class Attachement {

    private Long id;
    private String link;
    private String image;
    private String video;
    private String document;
    private AttachementType type;

    public Attachement(Long id, String link, String image,
                       String video, String document, AttachementType type) {
        this.id = id;
        this.link = link;
        this.image = image;
        this.video = video;
        this.document = document;
        this.type = type;
    }

    public AttachementType getType() {
        return type;
    }

    public void setType(AttachementType type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}









