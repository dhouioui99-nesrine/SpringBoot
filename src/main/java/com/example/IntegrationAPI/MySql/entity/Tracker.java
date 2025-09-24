package com.example.IntegrationAPI.MySql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="trackers")
public class Tracker {
    @Id
    private Long id;
    private String name;
    private String description ;

    public Tracker(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Tracker() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
