package com.example.IntegrationAPI.Base3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departement")
public class Dep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codeDept;
    private String name;
    private boolean is_default;

    public Dep(Long id, String codeDept, String name, boolean is_default) {
        this.id = id;
        this.codeDept = codeDept;
        this.name = name;
        this.is_default = is_default;
    }

    public Dep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDept() {
        return codeDept;
    }

    public void setCodeDept(String codeDept) {
        this.codeDept = codeDept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }
}
