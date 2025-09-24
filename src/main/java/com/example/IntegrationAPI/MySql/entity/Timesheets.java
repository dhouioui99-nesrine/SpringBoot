package com.example.IntegrationAPI.MySql.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Immutable
@Table(name="easy_timesheets")
public class Timesheets {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String period;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime start;

    @Column(name = "end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime end;

    private int locked;

    public Timesheets(Long id, Users user, String period, LocalDateTime start, LocalDateTime end, int locked) {
        this.id = id;
        this.user = user;
        this.period = period;
        this.start = start;
        this.end = end;
        this.locked = locked;
    }

    public Timesheets() {
    }


    public Long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public String getPeriod() {
        return period;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getLocked() {
        return locked;
    }
}