package com.example.IntegrationAPI.Postgres.controller;

import com.example.IntegrationAPI.Postgres.service.PaycodeService;
import com.example.IntegrationAPI.Postgres.model.Paycode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/paycode")
public class PaycodeController {
    private final PaycodeService paycodeService;

    public PaycodeController(PaycodeService paycodeService ) {
        this.paycodeService = paycodeService;
    }

    @GetMapping
    public List<Paycode> getAllPaycode() {
        return paycodeService.getAllPaycode();
    }
}
