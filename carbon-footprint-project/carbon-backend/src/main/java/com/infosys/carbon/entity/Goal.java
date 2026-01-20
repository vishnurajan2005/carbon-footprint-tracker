package com.infosys.carbon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double targetCarbon;

    @Column(nullable = false)
    private Double currentCarbon;

    @Column(nullable = false)
    private Double progressPercent;

    @Column(nullable = false)
    private String status; // IN_PROGRESS / COMPLETED

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Automatically set defaults before saving
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.currentCarbon = 0.0;
        this.progressPercent = 0.0;
        this.status = "IN_PROGRESS";
    }

    // ===== Getters =====
    public Long getId() {
        return id;
    }

    public Double getTargetCarbon() {
        return targetCarbon;
    }

    public Double getCurrentCarbon() {
        return currentCarbon;
    }

    public Double getProgressPercent() {
        return progressPercent;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    // ===== Setters =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setTargetCarbon(Double targetCarbon) {
        this.targetCarbon = targetCarbon;
    }

    public void setCurrentCarbon(Double currentCarbon) {
        this.currentCarbon = currentCarbon;
    }

    public void setProgressPercent(Double progressPercent) {
        this.progressPercent = progressPercent;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser(User user) {
        this.user = user;
    }
}





