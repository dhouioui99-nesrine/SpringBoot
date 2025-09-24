package com.example.IntegrationAPI.Postgres.service;

import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.model.Timeinterval;
import com.example.IntegrationAPI.Postgres.model.Workflow;
import com.example.IntegrationAPI.Postgres.repository.TimeintervalRepository;
import com.example.IntegrationAPI.Postgres.repository.WorkflowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WorkflowService {
    private final WorkflowRepository workflowRepository;

    public WorkflowService(WorkflowRepository workflowRepository ) {
        this.workflowRepository = workflowRepository;
    }

    public List<Workflow> getAllWorkflow() {
        return workflowRepository.findAll();
    }




    //get empCode

    public List<Workflow> getWorkflowsByEmpCode(String empCode) {
        return workflowRepository.findByEmployee_EmpCode(empCode);
    }
}