package com.example.IntegrationAPI.MySql.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name="easy_attendances")
public class Attendance {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "easy_attendance_activity_id")
    private Attendance_Act idAct;
    @Column(name = "arrival")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate arrival;
    @Column(name = "departure")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate departure;
    private String description;


    public Attendance(Long id, Users user, Attendance_Act idAct, LocalDate arrival, LocalDate departure, String description) {
        this.id = id;
        this.user = user;
        this.idAct = idAct;
        this.arrival = arrival;
        this.departure = departure;
        this.description = description;
    }


    public Attendance() {
    }

    public Attendance_Act getIdAct() {
        return idAct;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public String getDescription() {
        return description;
    }

    public Users getUser() {
        return user;
    }
}

