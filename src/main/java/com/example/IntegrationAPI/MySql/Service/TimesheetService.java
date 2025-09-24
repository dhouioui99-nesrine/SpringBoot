package com.example.IntegrationAPI.MySql.Service;

import com.example.IntegrationAPI.MySql.Repository.AttendanceRepository;
import com.example.IntegrationAPI.MySql.Repository.TimesheetRepository;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    @Autowired
    public TimesheetService(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public List<Timesheets> getAllTime() {
        return timesheetRepository.findAll();
    }
}