package com.example.IntegrationAPI.Postgres.service;



import com.example.IntegrationAPI.Postgres.model.Shift;
import com.example.IntegrationAPI.Postgres.repository.ShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getAllShift() {
        return shiftRepository.findAll();
    }
}


