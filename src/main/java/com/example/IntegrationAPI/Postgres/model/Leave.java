package com.example.IntegrationAPI.Postgres.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Immutable
@Table(name="att_leave")
public class Leave {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workflowinstance_ptr_id;

    @OneToOne
    @JoinColumn(name = "workflowinstance_ptr_id")
    private Workflow workflow;

    @Column(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDate start;

    @Column(name = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd  HH:mm:ss")
    private LocalDate  end;

    @Column(name = "apply_reason")
    private String reason ;

    @Column(name = "leave_day")
    private Double day ;
    @OneToOne
    @JoinColumn(name = "pay_code_id")
    private Paycode paycode;

    public Leave(Workflow workflow, LocalDate start, LocalDate end, String reason, Double day , Paycode paycode) {
        this.workflow = workflow;
        this.start = start;
        this.end = end;
        this.reason = reason;
        this.day = day;
        this.paycode=paycode;
    }

    public Leave() {
    }


    public Workflow getWorkflow() {
        return workflow;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getReason() {
        return reason;
    }

    public Double getDay() {
        return day;
    }

    public Paycode getPaycode() {
        return paycode;
    }
}
