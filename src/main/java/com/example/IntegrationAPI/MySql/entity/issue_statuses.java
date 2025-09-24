package com.example.IntegrationAPI.MySql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="issue_statuses")
public class issue_statuses {

    @Id
    private Long id;
    private String name ;

    public issue_statuses(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public issue_statuses() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}