package com.example.IntegrationAPI.Postgres.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="personnel_department")
public class Departement {
    @Id
    private Long id;

    private String dept_code;
    private String dept_name;
    private boolean is_default;


    public Departement() {
    }

    public Departement(Long id, String dept_code, String dept_name, boolean is_default, int parent_dept_id) {
        this.id = id;
        this.dept_code = dept_code;
        this.dept_name = dept_name;
        this.is_default = is_default;

    }

    public String getDept_code() {
        return dept_code;
    }

    public Long getId() {
        return id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public boolean isIs_default() {
        return is_default;
    }

 
}
