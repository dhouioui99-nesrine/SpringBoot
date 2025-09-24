package com.example.IntegrationAPI.MySql.Service;

import com.example.IntegrationAPI.MySql.Repository.TimeUserRepository;
import com.example.IntegrationAPI.MySql.Repository.TimesheetRepository;
import com.example.IntegrationAPI.MySql.entity.TimeUser;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TimeUserService {

    private final TimeUserRepository timeUserRepository;
    @Autowired
    public TimeUserService(TimeUserRepository timeUserRepository) {
        this.timeUserRepository = timeUserRepository;
    }

    public List<TimeUser> getAllTime() {
        return timeUserRepository.findAll();
    }
}