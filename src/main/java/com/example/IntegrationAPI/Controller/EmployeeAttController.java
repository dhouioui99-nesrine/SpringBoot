package com.example.IntegrationAPI.Controller;

import com.example.IntegrationAPI.Dto.EmployeeAtt;
import com.example.IntegrationAPI.Dto.EmployeeUser;
import com.example.IntegrationAPI.Service.EmployeeAttService;
import com.example.IntegrationAPI.Service.user_empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empAtt")
public class EmployeeAttController {
    private final EmployeeAttService employeeAttService;

    @Autowired
    public EmployeeAttController(EmployeeAttService employeeAttService) {
        this.employeeAttService = employeeAttService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllEmployees() {
        return ResponseEntity.ok(employeeAttService.getAllEmployees());
    }

    @GetMapping("/matching")
    public ResponseEntity<List<EmployeeAtt>> getMatchingEmployees() {
        List<EmployeeAtt> employees = employeeAttService.getMatchingEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/matching/{empCode}")
    public ResponseEntity<List<EmployeeAtt>> getMatchingEmployeeAttendance(@PathVariable String empCode) {
        List<EmployeeAtt> result = employeeAttService.getAttendancesByEmpCode(empCode);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }
}

