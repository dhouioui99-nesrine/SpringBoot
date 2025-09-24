package com.example.IntegrationAPI.Postgres.service;

import com.example.IntegrationAPI.Postgres.model.Paycode;
import com.example.IntegrationAPI.Postgres.repository.PaycodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaycodeService {
    private final PaycodeRepository paycodeRepository;
    @Autowired
    public PaycodeService(PaycodeRepository paycodeRepository ) {
        this.paycodeRepository = paycodeRepository;
    }

    public List<Paycode> getAllPaycode() {
        return paycodeRepository.findAll();
    }
}
