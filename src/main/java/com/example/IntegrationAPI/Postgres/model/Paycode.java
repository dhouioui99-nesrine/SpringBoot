package com.example.IntegrationAPI.Postgres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.time.LocalTime;

@Entity
@Immutable
@Table(name="att_paycode")
public class Paycode {
    @Id
    private Long id;

    private String name;
    @Column(name = "fixed_hours")
    private float hours ;

    public Paycode(Long id, String name, float hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public Paycode() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getHours() {
        return hours;
    }
}
