package com.example.IntegrationAPI.Base3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "statut_tache")
public class issue_stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;


    public issue_stat(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public issue_stat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
