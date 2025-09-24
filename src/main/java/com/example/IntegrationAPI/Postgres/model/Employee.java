package com.example.IntegrationAPI.Postgres.model;


import jakarta.persistence.*;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="personnel_employee")
public class Employee {

    @Id
    private Long id;
    @Column(name = "emp_code")
    private String empCode;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    private String email;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departement department;
    public Employee() {

    }

    public Employee(Long id, String empCode, String firstname, String lastname, String email, Departement department) {
        this.id = id;
        this.empCode = empCode;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.department = department;
    }

    public Departement getDepartment() {
        return department;
    }

    public Long getId() {
        return id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }


}
