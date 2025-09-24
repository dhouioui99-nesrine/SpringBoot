package com.example.IntegrationAPI.Postgres.controller;

import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.model.Timeinterval;
import com.example.IntegrationAPI.Postgres.model.Workflow;
import com.example.IntegrationAPI.Postgres.service.TimeintervalService;
import com.example.IntegrationAPI.Postgres.service.WorkflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Controller
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/workflow")
public class WorkflowController {
    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService ) {
        this.workflowService = workflowService;
    }

    @GetMapping
    public List<Workflow> getAllWorkflow() {
        return workflowService.getAllWorkflow();
    }



    //empCode

    @GetMapping("/{empCode}")
    public List<Workflow> getWorkflowsByEmpCode(@PathVariable String empCode) {
        return workflowService.getWorkflowsByEmpCode(empCode);
    }
}
