package com.example.IntegrationAPI.Postgres.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalTime;

@Entity
@Immutable
@Table(name="att_shiftdetail")
public class Shiftdetail {

    @Id
    private Long id;


    private LocalTime in_time;
    private LocalTime out_time;
    private int day_index;
    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift_id;

    @ManyToOne
    @JoinColumn(name = "time_interval_id")
    private Timeinterval time_interval_id;

    public Shiftdetail() {
    }

    public Shiftdetail(Long id, LocalTime in_time, LocalTime out_time, int day_index, Shift shift_id, Timeinterval time_interval_id) {
        this.id = id;
        this.in_time = in_time;
        this.out_time = out_time;
        this.day_index = day_index;
        this.shift_id = shift_id;
        this.time_interval_id = time_interval_id;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getIn_time() {
        return in_time;
    }

    public LocalTime getOut_time() {
        return out_time;
    }

    public int getDay_index() {
        return day_index;
    }

    public Shift getShift_id() {
        return shift_id;
    }

    public Timeinterval getTime_interval_id() {
        return time_interval_id;
    }
}