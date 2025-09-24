package com.example.IntegrationAPI.Postgres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.time.ZonedDateTime;

@Entity
@Immutable
@Table(name="iclock_transaction")

public class iclock_transaction {

    @Id
    private Integer id;
    @Column(name = "emp_code")
    private String empCode;
    private Integer terminal_id;
    @Column(name = "emp_id")
    private Integer empId;
    private ZonedDateTime punch_time;

    public iclock_transaction(Integer id, String empCode, Integer terminal_id, Integer empId, ZonedDateTime punch_time) {
        this.id = id;
        this.empCode = empCode;
        this.terminal_id = terminal_id;
        this.empId = empId;
        this.punch_time = punch_time;
    }

    public iclock_transaction() {
    }

    public Integer getId() {
        return id;
    }

    public String getEmpCode() {
        return empCode;
    }

    public Integer getTerminal_id() {
        return terminal_id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public ZonedDateTime getPunch_time() {
        return punch_time;
    }
}
