package com.example.IntegrationAPI.Postgres.model;

import jakarta.persistence.* ;
import org.hibernate.annotations.Immutable;
import java.time.LocalTime;
@Entity
@Immutable
@Table(name="att_breaktime")
public class Breaktime {
    @Id
    private Long id;

    private String alias;
    private LocalTime  period_start;
    private int end_margin;
    private int duration;

    public Breaktime(Long id, String alias, int duration,int end_margin, LocalTime period_start) {
        this.id = id;
        this.alias = alias;
        this.duration = duration;
        this.end_margin = end_margin;
        this.period_start = period_start;
    }

    public Breaktime() {
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public LocalTime getPeriod_start() {
        return period_start;
    }

    public int getDuration() {
        return duration;
    }

    public int getEnd_margin() {
        return end_margin;
    }
}
