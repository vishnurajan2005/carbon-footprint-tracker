package com.infosys.carbon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String badgeName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime awardedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Creates a badge for a user when a defined progress milestone is achieved
    public Badge(LocalDateTime awardedAt, String badgeName, String description, Long id, User user) {
        this.awardedAt = awardedAt;
        this.badgeName = badgeName;
        this.description = description;
        this.id = id;
        this.user = user;
    }

    @PrePersist
    protected void onAward() {
        this.awardedAt = LocalDateTime.now();
    }

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getAwardedAt() {
        return awardedAt;
    }

    public User getUser() {
        return user;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }
}





