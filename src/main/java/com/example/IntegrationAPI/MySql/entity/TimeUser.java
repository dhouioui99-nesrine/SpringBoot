package com.example.IntegrationAPI.MySql.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Immutable
@Table(name="easy_user_time_calendars")
public class TimeUser {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "default_working_hours")
    private float hours;

    @Column(name = "time_from")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime debut;

    @Column(name = "time_to")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime  fin;

    public TimeUser(Long id, String name, Users user, float hours, LocalDateTime  debut, LocalDateTime  fin) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.hours = hours;
        this.debut = debut;
        this.fin = fin;
    }

    public TimeUser() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Users getUser() {
        return user;
    }

    public float getHours() {
        return hours;
    }

    public LocalDateTime  getDebut() {
        return debut;
    }

    public LocalDateTime  getFin() {
        return fin;
    }
}