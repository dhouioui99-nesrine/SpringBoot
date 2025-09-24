package com.example.IntegrationAPI.MySql.Controller;

import com.example.IntegrationAPI.MySql.Service.TimeUserService;
import com.example.IntegrationAPI.MySql.Service.TimesheetService;
import com.example.IntegrationAPI.MySql.entity.TimeUser;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/timeuser")
@CrossOrigin(origins = "http://localhost:4200")
public class TimeUserController {
    private final TimeUserService timeUserService;

    public TimeUserController(TimeUserService timeUserService) {
        this.timeUserService = timeUserService;
    }

    @GetMapping
    public List<TimeUser> getAllTime() {
        return timeUserService.getAllTime();
    }
}