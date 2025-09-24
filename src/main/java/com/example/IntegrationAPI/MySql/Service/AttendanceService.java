package com.example.IntegrationAPI.MySql.Service;


import com.example.IntegrationAPI.MySql.Repository.AttendanceRepository;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}