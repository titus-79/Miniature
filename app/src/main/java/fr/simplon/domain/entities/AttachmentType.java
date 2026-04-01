package fr.simplon.domain.entities;

public enum AttachmentType {
    
    LINK("lien", false),
    IMAGE("image", true),
    VIDEO("vidéo", true),
    DOCUMENT("document", false),
    POST("publication", false);

    private final String label;
    private final boolean isMedia;

    AttachmentType(String label, boolean isMedia) {
        this.label = label;
        this.isMedia = isMedia;
    }

    public String getLabel() { return label; }
    public boolean isMedia() { return isMedia; }
}