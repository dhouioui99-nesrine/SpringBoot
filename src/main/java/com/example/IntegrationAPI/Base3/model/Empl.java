package com.example.IntegrationAPI.Base3.model;

import com.example.IntegrationAPI.Postgres.model.Departement;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Empl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empCode;

    private String firstname;

    private String lastname;
    private String email;

    private String department;


    public Empl() {

    }

    public Empl(Long id, String empCode, String firstname, String lastname, String email, String department) {
        this.id = id;
        this.empCode = empCode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
