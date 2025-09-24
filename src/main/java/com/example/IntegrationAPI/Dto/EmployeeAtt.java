package com.example.IntegrationAPI.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeAtt {

    private String empCode;
    private String attendance ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate arrival;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate departure;
    private String description;
    private String lastname;
    private String department;

    public EmployeeAtt(String empCode, String attendance, LocalDate arrival, LocalDate departure, String description, String lastname, String department) {
        this.empCode = empCode;
        this.attendance = attendance;
        this.arrival = arrival;
        this.departure = departure;
        this.description = description;
        this.lastname = lastname;
        this.department = department;
    }

    public EmployeeAtt() {
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getAttendance() {
        return attendance;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public String getDescription() {
        return description;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }
}
