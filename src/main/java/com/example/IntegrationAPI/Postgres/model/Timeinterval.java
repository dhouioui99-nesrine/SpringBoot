package com.example.IntegrationAPI.Postgres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.time.LocalTime;

@Entity
@Immutable
@Table(name="att_timeinterval")
public class Timeinterval {
    @Id
    private Long id;

    private String alias;
    private LocalTime in_time;
    private int in_ahead_margin;
    private int in_above_margin;
    private int out_ahead_margin;
    private int out_above_margin;
    private int duration;
    private int work_time_duration;

    public Timeinterval(Long id, String alias, LocalTime in_time, int in_ahead_margin, int in_above_margin, int out_ahead_margin, int out_above_margin, int duration, int work_time_duration) {
        this.id = id;
        this.alias = alias;
        this.in_time = in_time;
        this.in_ahead_margin = in_ahead_margin;
        this.in_above_margin = in_above_margin;
        this.out_ahead_margin = out_ahead_margin;
        this.out_above_margin = out_above_margin;
        this.duration = duration;
        this.work_time_duration = work_time_duration;
    }

    public Timeinterval() {
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public LocalTime getIn_time() {
        return in_time;
    }

    public int getIn_ahead_margin() {
        return in_ahead_margin;
    }

    public int getIn_above_margin() {
        return in_above_margin;
    }

    public int getOut_ahead_margin() {
        return out_ahead_margin;
    }

    public int getOut_above_margin() {
        return out_above_margin;
    }

    public int getDuration() {
        return duration;
    }

    public int getWork_time_duration() {
        return work_time_duration;
    }
}
