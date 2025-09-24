package com.example.IntegrationAPI.Postgres.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.* ;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;


@Entity
@Immutable
@Table(name="workflow_workflowinstance")
public class Workflow {
    @Id
    @Column(name = "id")
    private Long idWorkflow;


    @Column(name = "approval_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDateTime time;

    private  String approver ;


    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public Workflow(Long idWorkflow, LocalDateTime time, String approver, Employee employee) {
        this.idWorkflow = idWorkflow;
        this.time = time;
        this.approver = approver;
        this.employee = employee;
    }

    public Workflow() {
    }

    public Long getIdWorkflow() {
        return idWorkflow;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getApprover() {
        return approver;
    }

    public Employee getEmployee() {
        return employee;
    }
}