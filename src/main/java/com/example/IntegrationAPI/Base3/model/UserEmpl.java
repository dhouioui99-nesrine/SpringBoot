package com.example.IntegrationAPI.Base3.model;

import jakarta.persistence.*;


@Entity
@Table(name = "UserEmpl")
public class UserEmpl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long UserId;
    private String empCode;
    private String lastname;
    private String department;

    public UserEmpl(Long id, Long userId, String empCode, String lastname, String department) {
        this.id = id;
        UserId = userId;
        this.empCode = empCode;
        this.lastname = lastname;
        this.department = department;
    }

    public UserEmpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}