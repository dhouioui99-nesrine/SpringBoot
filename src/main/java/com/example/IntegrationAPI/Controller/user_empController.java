package com.example.IntegrationAPI.Controller;
import com.example.IntegrationAPI.Dto.EmployeeUser;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import com.example.IntegrationAPI.MySql.entity.Users;
import com.example.IntegrationAPI.Postgres.model.Employee;
import  com.example.IntegrationAPI.Service.user_empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class user_empController {

    private final user_empService userEmpService;

    @Autowired
    public user_empController(user_empService userEmpService) {
        this.userEmpService = userEmpService;
    }

    @GetMapping
    public ResponseEntity<List<Object>> getAllEmployees() {
        return ResponseEntity.ok(userEmpService.getAllEmployees());
    }

    @GetMapping("/matching")
    public ResponseEntity<List<EmployeeUser>> getMatchingEmployees() {
        List<EmployeeUser> employees = userEmpService.getMatchingEmployees();
        return ResponseEntity.ok(employees);
    }
}
