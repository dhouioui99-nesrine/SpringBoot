package com.example.IntegrationAPI.Postgres.controller;


import com.example.IntegrationAPI.Postgres.model.Breaktime;
import com.example.IntegrationAPI.Postgres.service.BreaktimeService;
import org.springframework.web.bind.annotation.* ;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Breaktime")
public class BreaktimeController {
    private final BreaktimeService breaktimeService;

    public BreaktimeController(BreaktimeService breaktimeService ) {
        this.breaktimeService = breaktimeService;
    }

    @GetMapping
    public List<Breaktime> getAllBreaktime() {
        return breaktimeService.getAllBreaktime();
    }
}
