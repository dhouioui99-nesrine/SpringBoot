package com.example.IntegrationAPI.Base3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "conges")
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empCode ;
    private String firstname;
    private String Approver;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;
    private String reason ;

    private Double day ;
    private String paycode;
    private String Source;

    public Conge(String empCode,String firstname, String Approver, LocalDate  start, LocalDate  end, String reason, Double day, String paycode, String source) {
        this.empCode = empCode;
        this.firstname=firstname;
        Approver = Approver;
        this.start = start;
        this.end = end;
        this.reason = reason;
        this.day = day;
        this.paycode = paycode;
        Source = source;
    }


    public Conge() {
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getApprover() {
        return Approver;
    }

    public void setApprover(String approver) {
        Approver = approver;
    }

    public LocalDate  getStart() {
        return start;
    }

    public void setStart(LocalDate  start) {
        this.start = start;
    }

    public LocalDate  getEnd() {
        return end;
    }

    public void setEnd(LocalDate  end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public String getPaycode() {
        return paycode;
    }

    public void setPaycode(String paycode) {
        this.paycode = paycode;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}