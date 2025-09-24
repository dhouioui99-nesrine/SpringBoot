package com.example.IntegrationAPI.Postgres.controller;


import com.example.IntegrationAPI.Postgres.model.Departement;
import com.example.IntegrationAPI.Postgres.service.DepartementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Departement")
public class DepartementController    {
    private final DepartementService departementService;

public DepartementController(DepartementService departementService) {
    this.departementService = departementService;
}

@GetMapping
public List<Departement> getAllDepartement() {
    return departementService.getAllDepartement();
}
}
