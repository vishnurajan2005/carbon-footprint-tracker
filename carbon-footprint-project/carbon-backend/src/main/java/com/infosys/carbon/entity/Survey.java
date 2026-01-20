package com.infosys.carbon.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transportMode;

    private String dietType;

    private Double energyUsage;

    private String frequency;

    // 🔑 VERY IMPORTANT: USER MAPPING
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime createdAt;

    // ⏱ Auto timestamp
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ======================
    // GETTERS
    // ======================

    public Long getId() {
        return id;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public String getDietType() {
        return dietType;
    }

    public Double getEnergyUsage() {
        return energyUsage;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    // ======================
    // SETTERS
    // ======================

    public void setId(Long id) {
        this.id = id;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public void setEnergyUsage(Double energyUsage) {
        this.energyUsage = energyUsage;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


