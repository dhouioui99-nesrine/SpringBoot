package com.example.IntegrationAPI.Postgres.service;





import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

@Service

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    // Déclaration de l'ensemble des départements à exclure
    private static final Set<String> EXCLUDED_DEPARTMENTS = Set.of(
            "OTH", "Appli_Groupe", "Infra_Groupe", "Innov_Groupe", "stagiaire"
    );

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        // Récupérer tous les employés
        List<Employee> employees = employeeRepository.findAll();

        // Filtrer les employés dont le département est dans la liste des départements à exclure
        return employees.stream()
                .filter(employee -> employee.getDepartment() != null &&
                        !EXCLUDED_DEPARTMENTS.contains(employee.getDepartment().getDept_name()))
                .collect(Collectors.toList());
    }




    public List<Employee> getEmployeeById(String empCode) {
        return employeeRepository.findByEmpCode(empCode);
    }
}