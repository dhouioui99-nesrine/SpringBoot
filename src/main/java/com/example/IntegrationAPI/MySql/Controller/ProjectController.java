package com.example.IntegrationAPI.MySql.Controller;

import com.example.IntegrationAPI.MySql.Service.IssuesService;
import com.example.IntegrationAPI.MySql.Service.ProjetService;
import com.example.IntegrationAPI.MySql.entity.Project;
import com.example.IntegrationAPI.MySql.entity.issues;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projet")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
    private final ProjetService service;

    public ProjectController(ProjetService service ) {
        this.service = service;
    }

    @GetMapping
    public List<Project> getAll() {
        return service.getAll();
    }
}

