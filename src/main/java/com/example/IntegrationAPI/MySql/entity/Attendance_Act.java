package com.example.IntegrationAPI.MySql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="easy_attendance_activities")
public class Attendance_Act {
    @Id
    private Long id;
    private String name ;

    public Attendance_Act(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Attendance_Act() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

