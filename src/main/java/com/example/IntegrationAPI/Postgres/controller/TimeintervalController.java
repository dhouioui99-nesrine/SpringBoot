package com.example.IntegrationAPI.Postgres.controller;



import com.example.IntegrationAPI.Postgres.model.Timeinterval;
import com.example.IntegrationAPI.Postgres.service.TimeintervalService;
import org.springframework.web.bind.annotation.* ;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Timeinterval")
public class TimeintervalController {
    private final TimeintervalService timeintervalService;

    public TimeintervalController(TimeintervalService timeintervalService ) {
        this.timeintervalService = timeintervalService;
    }

    @GetMapping
    public List<Timeinterval> getAllTimeinterval() {
        return timeintervalService.getAllTimeinterval();
    }
}
