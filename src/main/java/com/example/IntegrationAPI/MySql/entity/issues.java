package com.example.IntegrationAPI.MySql.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Entity
@Immutable
@Table(name="issues")
public class issues {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tracker_id")
    private Tracker trackerId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;

    private String subject ;
    private String description ;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date due_date ;


    @ManyToOne
    @JoinColumn(name = "status_id")
    private issue_statuses statut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date start_date ;
    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private Users assigned;
    public issues(Long id, Tracker trackerId, Project projectId, String subject, String description, Date due_date, issue_statuses statut, Date start_date,Users assigned) {
        this.id = id;
        this.trackerId = trackerId;
        this.projectId = projectId;
        this.subject = subject;
        this.description = description;
        this.due_date = due_date;
        this.statut = statut;
        this.start_date = start_date;
        this.assigned=assigned;
    }

    public issues() {
    }

    public Long getId() {
        return id;
    }

    public Tracker getTrackerId() {
        return trackerId;
    }

    public Project getProjectId() {
        return projectId;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public Date getDue_date() {
        return due_date;
    }

    public issue_statuses getStatut() {
        return statut;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Users getAssigned() {
        return assigned;
    }
}