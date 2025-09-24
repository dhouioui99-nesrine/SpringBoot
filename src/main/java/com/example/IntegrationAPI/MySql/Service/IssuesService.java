package com.example.IntegrationAPI.MySql.Service;

import com.example.IntegrationAPI.MySql.Repository.AttendanceRepository;
import com.example.IntegrationAPI.MySql.Repository.IssuesRepository;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import com.example.IntegrationAPI.MySql.entity.issues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IssuesService {
    private final IssuesRepository issuesRepository;
    @Autowired
    public IssuesService(IssuesRepository issuesRepository) {
        this.issuesRepository = issuesRepository;
    }

    public List<issues> getAll() {
        return issuesRepository.findAll();
    }
}
