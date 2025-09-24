package com.example.IntegrationAPI.Controller;

import com.example.IntegrationAPI.Dto.EmpTimesheet;
import com.example.IntegrationAPI.Dto.EmployeeAtt;
import com.example.IntegrationAPI.Service.EmpTimesheetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emptime")
public class EmpTimesheetController {
    private final EmpTimesheetService empTimesheetService ;

    public EmpTimesheetController(EmpTimesheetService empTimesheetService) {
        this.empTimesheetService = empTimesheetService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllEmployees() {
        return ResponseEntity.ok(empTimesheetService.getAllEmployees());
    }
    @GetMapping("/matching")
    public ResponseEntity<List<EmpTimesheet>> getMatchingEmployees() {
        List<EmpTimesheet> employees = empTimesheetService.getMatchingEmployeesFromTimesheets();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/matching/{empCode}")
    public ResponseEntity<List<EmpTimesheet>> getMatchingEmployeesFromTimesheets(@PathVariable String empCode) {
        List<EmpTimesheet> result = empTimesheetService.getMatchingEmployeesFromTimesheets(empCode);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }
}
