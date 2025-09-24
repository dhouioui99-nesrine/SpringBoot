package com.example.IntegrationAPI.MySql.Controller;


import com.example.IntegrationAPI.MySql.Service.AttendanceService;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import org.springframework.web.bind.annotation.* ;

import java.util.List;


@RestController
@RequestMapping("/api/att")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService ) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }
}