package com.example.IntegrationAPI.Postgres.service;



import com.example.IntegrationAPI.Postgres.model.Breaktime;
import com.example.IntegrationAPI.Postgres.repository.BreaktimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BreaktimeService {
    private final BreaktimeRepository breaktimeRepository;
@Autowired
    public BreaktimeService(BreaktimeRepository breaktimeRepository) {
        this.breaktimeRepository = breaktimeRepository;
    }

    public List<Breaktime> getAllBreaktime() {
        return breaktimeRepository.findAll();
    }
}
