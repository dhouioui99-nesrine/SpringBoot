package com.example.IntegrationAPI.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class EmpTimesheet {


    private String empCode;
    private String periode  ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime start ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     private LocalDateTime end;
    private Long userId;
    private String  lastname;

    public EmpTimesheet(String empCode, String periode, LocalDateTime start, LocalDateTime end, Long userId, String lastname) {
        this.empCode = empCode;
        this.periode = periode;
        this.start = start;
        this.end = end;
        this.userId = userId;
        this.lastname = lastname;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getPeriode() {
        return periode;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLastname() {
        return lastname;
    }

    public EmpTimesheet() {
    }
}
