package com.example.IntegrationAPI.MySql.Service;


import com.example.IntegrationAPI.MySql.Repository.TrackerRepository;
import com.example.IntegrationAPI.MySql.entity.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackerService {
    private final TrackerRepository repo;
    @Autowired
    public TrackerService(TrackerRepository repo) {
        this.repo = repo;
    }

    public List<Tracker> getAll() {
        return repo.findAll();
    }
}
