package com.example.IntegrationAPI.Postgres.controller;




import com.example.IntegrationAPI.Postgres.model.Shift;
import com.example.IntegrationAPI.Postgres.service.ShiftService;
import org.springframework.web.bind.annotation.* ;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/Shift")

public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService ) {
        this.shiftService = shiftService;
    }

    @GetMapping
    public List<Shift> getAllShift() {
        return shiftService.getAllShift();
    }
}
