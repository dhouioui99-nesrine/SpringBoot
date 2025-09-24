package com.example.IntegrationAPI.Postgres.service;

import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.model.Leave;
import com.example.IntegrationAPI.Postgres.model.Workflow;
import com.example.IntegrationAPI.Postgres.repository.LeaveRepository;
import com.example.IntegrationAPI.Postgres.repository.WorkflowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LeaveService {
    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository ) {
        this.leaveRepository = leaveRepository;
    }

    public List<Leave> getAllLeave() {
        return leaveRepository.findAll();
    }

    public List<Leave> getLeaveByEmpCode(String empCode) {
        return leaveRepository.findLeaveByEmpCode(empCode);
    }
}
