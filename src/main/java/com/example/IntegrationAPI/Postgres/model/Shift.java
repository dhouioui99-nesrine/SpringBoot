package com.example.IntegrationAPI.Postgres.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name="att_attshift")
public class Shift {
    @Id
    private Long id;

    private String alias;
    private int cycle_unit;
    private int shift_cycle;
    private boolean work_weekend ;
    private int weekend_type;
    private boolean work_day_off ;

    public Shift() {
    }

    public Shift(boolean work_day_off, int weekend_type, boolean work_weekend, int shift_cycle, int cycle_unit, String alias, Long id) {
        this.work_day_off = work_day_off;
        this.weekend_type = weekend_type;
        this.work_weekend = work_weekend;
        this.shift_cycle = shift_cycle;
        this.cycle_unit = cycle_unit;
        this.alias = alias;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public int getCycle_unit() {
        return cycle_unit;
    }

    public int getShift_cycle() {
        return shift_cycle;
    }

    public boolean isWork_weekend() {
        return work_weekend;
    }

    public int getWeekend_type() {
        return weekend_type;
    }

    public boolean isWork_day_off() {
        return work_day_off;
    }
}
