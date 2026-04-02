package fr.simplon.domain.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String userName;
    private String email;
    private String passwordHash;
    private LocalDateTime createAt;
    private final List<User> following = new ArrayList<>();

    public User(Long id, String userName, String email,
            String passwordHash, LocalDateTime createAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.createAt = createAt;
    }

    public void follow(User target) {
        if (target == null || target.getId().equals(this.id))
            return;
        if (!isFollowing(target)) {
            following.add(target);
        }
    }

    public void unfollow(User target) {
        if (target == null)
            return;
        following.removeIf(u -> u.getId().equals(target.getId()));
    }

    public boolean isFollowing(User target) {
        if (target == null)
            return false;
        return following.stream().anyMatch(u -> u.getId().equals(target.getId()));
    }

    public List<User> getFollowing() {
        return following;
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

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", email=" + email
                + ", passwordHash=" + passwordHash + ", createAt=" + createAt + "]";
    }

}