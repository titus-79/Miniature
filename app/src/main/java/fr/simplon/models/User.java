package fr.simplon.models;

import java.time.LocalDateTime;

public class User {

    Long id;
    String userName;
    String email;
    String passwordHash;
    LocalDateTime createAt;

    public User(Long id, String userName, String email,
                String passwordHash, LocalDateTime createAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createAt = createAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public static void like(Post post){
        //TODO
    }
    public static void comment(Post post, String content){
        //TODO
    }
    public static void follow(User target){
        //TODO
    }
    public static void unfollow(User target){
        //TODO
    }
    public static void createPost(String content){
        //TODO
    }
}
