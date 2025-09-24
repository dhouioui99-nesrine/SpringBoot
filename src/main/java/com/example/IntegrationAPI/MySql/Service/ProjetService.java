package com.example.IntegrationAPI.MySql.Service;


import com.example.IntegrationAPI.MySql.Repository.ProjectRepository;
import com.example.IntegrationAPI.MySql.entity.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjetService {
    private final ProjectRepository repo;
    @Autowired
    public ProjetService(ProjectRepository repo) {
        this.repo = repo;
    }

    public List<Project> getAll() {
        return repo.findAll();
    }
}