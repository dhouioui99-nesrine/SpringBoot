package com.example.IntegrationAPI.Postgres.service;




import com.example.IntegrationAPI.Postgres.model.Timeinterval;
import com.example.IntegrationAPI.Postgres.repository.TimeintervalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TimeintervalService {
    private final TimeintervalRepository timeintervalRepository;

    public TimeintervalService(TimeintervalRepository timeintervalRepository ) {
        this.timeintervalRepository = timeintervalRepository;
    }

    public List<Timeinterval> getAllTimeinterval() {
        return timeintervalRepository.findAll();
    }
}

