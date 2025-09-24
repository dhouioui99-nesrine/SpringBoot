package com.example.IntegrationAPI.Postgres.service;



import com.example.IntegrationAPI.Postgres.model.Shiftdetail;
import com.example.IntegrationAPI.Postgres.repository.ShiftdetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftdetailService {

    private final ShiftdetailRepository shiftdetailRepository;

    public ShiftdetailService(ShiftdetailRepository shiftdetailRepository) {
        this.shiftdetailRepository = shiftdetailRepository;
    }

    public List<Shiftdetail> getAllShiftdetail() {
        return shiftdetailRepository.findAll();
    }
}
