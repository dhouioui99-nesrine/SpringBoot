package com.example.IntegrationAPI.MySql.Controller;

import com.example.IntegrationAPI.MySql.Service.AttendanceService;
import com.example.IntegrationAPI.MySql.Service.TimesheetService;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/time")
@CrossOrigin(origins = "http://localhost:4200")
public class TimesheetController {
    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService ) {
        this.timesheetService = timesheetService;
    }

    @GetMapping
    public List<Timesheets> getAllTime() {
        return timesheetService.getAllTime();
    }
}