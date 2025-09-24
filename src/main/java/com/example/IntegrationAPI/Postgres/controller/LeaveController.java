package com.example.IntegrationAPI.Postgres.controller;

import com.example.IntegrationAPI.Postgres.model.Leave;
import com.example.IntegrationAPI.Postgres.model.Workflow;
import com.example.IntegrationAPI.Postgres.service.LeaveService;
import com.example.IntegrationAPI.Postgres.service.WorkflowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Controller
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public List<Leave> getAllLeave() {
        return leaveService.getAllLeave();
    }


    @GetMapping("/{empCode}")
    public ResponseEntity<List<Leave>> getLeaveByEmpCode(@PathVariable("empCode") String empCode) {
        List<Leave> leaves = leaveService.getLeaveByEmpCode(empCode);
        return (!leaves.isEmpty()) ? ResponseEntity.ok(leaves) : ResponseEntity.notFound().build();
    }



}