package com.infosys.carbon.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "carbon_logs")
public class CarbonLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double carbonAmount;

    private LocalDate logDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        this.logDate = LocalDate.now();
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public Double getCarbonAmount() {
        return carbonAmount;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public User getUser() {
        return user;
    }

    public void setCarbonAmount(Double carbonAmount) {
        this.carbonAmount = carbonAmount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

