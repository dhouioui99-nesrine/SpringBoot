package com.example.IntegrationAPI.MySql.Controller;

import com.example.IntegrationAPI.MySql.Service.AttendanceService;
import com.example.IntegrationAPI.MySql.Service.IssuesService;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import com.example.IntegrationAPI.MySql.entity.issues;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tache")
@CrossOrigin(origins = "http://localhost:4200")
public class IssuesController {
    private final IssuesService service;

    public IssuesController(IssuesService service ) {
        this.service = service;
    }

    @GetMapping
    public List<issues> getAll() {
        return service.getAll();
    }
}
