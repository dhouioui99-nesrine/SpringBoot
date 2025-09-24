package com.example.IntegrationAPI.MySql.Controller;

import com.example.IntegrationAPI.MySql.Service.ProjetService;
import com.example.IntegrationAPI.MySql.Service.TrackerService;
import com.example.IntegrationAPI.MySql.entity.Project;
import com.example.IntegrationAPI.MySql.entity.Tracker;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
@CrossOrigin(origins = "http://localhost:4200")
public class TrackerController {
    private final TrackerService service;

    public TrackerController(TrackerService service ) {
        this.service = service;
    }

    @GetMapping
    public List<Tracker> getAll() {
        return service.getAll();
    }
}

