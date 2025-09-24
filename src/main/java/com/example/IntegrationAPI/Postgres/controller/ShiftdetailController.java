package com.example.IntegrationAPI.Postgres.controller;



import com.example.IntegrationAPI.Postgres.model.Shiftdetail;
import com.example.IntegrationAPI.Postgres.service.ShiftdetailService;
import org.springframework.web.bind.annotation.* ;

import java.util.List;

@RestController
@RequestMapping("/api/Shiftdetail")
@CrossOrigin(origins = "http://localhost:4200")
public class ShiftdetailController {
    private final ShiftdetailService shiftdetailService;

    public ShiftdetailController(ShiftdetailService shiftdetailService ) {
        this.shiftdetailService = shiftdetailService;
    }

    @GetMapping
    public List<Shiftdetail> getAllShiftdetail() {
        return shiftdetailService.getAllShiftdetail();
    }
}
