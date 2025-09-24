package com.example.IntegrationAPI.Service;

import com.example.IntegrationAPI.Dto.EmpTimesheet;
import com.example.IntegrationAPI.Dto.EmployeeAtt;
import com.example.IntegrationAPI.MySql.Repository.AttendanceRepository;
import com.example.IntegrationAPI.MySql.Repository.TimesheetRepository;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpTimesheetService {

    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;

    @Autowired
    public EmpTimesheetService(EmployeeRepository employeeRepository, TimesheetRepository timesheetRepository) {
        this.employeeRepository = employeeRepository;
        this.timesheetRepository = timesheetRepository;
    }
    public List<Object> getAllEmployees() {
        List<Employee> employeesPostgres = employeeRepository.findAll();
        List<Timesheets> employeesMySQL = timesheetRepository.findAll();

        List<Object> allEmployees = new ArrayList<>();
        allEmployees.addAll(employeesPostgres);
        allEmployees.addAll(employeesMySQL);

        return allEmployees;
    }


    public List<EmpTimesheet> getMatchingEmployeesFromTimesheets(String empCode) {

            System.out.println("Recherche des présences pour empCode: " + empCode);

            List<Employee> employees = employeeRepository.findAll(); // Récupérer tous les employés (PostgreSQL)
            List<Timesheets> timesheets = timesheetRepository.findAll(); // Récupérer toutes les présences (MySQL)
            List<EmpTimesheet> matchingList = new ArrayList<>();

            // Définir la date minimale
            //LocalDateTime minDate = LocalDateTime.of(2024, 1, 1, 0, 0);

            for (Timesheets timesheet : timesheets) {
                // Vérifier si la date d’arrivée est après le 01/01/2024
                if (timesheet.getStart() != null
                    //    && timesheet.getStart().isAfter(minDate)
                ) {
                    String fullName = timesheet.getUser().getFirstname().trim() + " " + timesheet.getUser().getLastname().trim();

                    for (Employee employee : employees) {
                        if (employee.getEmpCode().equals(empCode) &&
                                employee.getLastname().trim().equalsIgnoreCase(fullName)) {

                            System.out.println("Match trouvé pour empCode: " + empCode);

                            matchingList.add(new EmpTimesheet(
                                    employee.getEmpCode(),
                                    timesheet.getPeriod(),
                                    timesheet.getStart(),
                                    timesheet.getEnd(),
                                    timesheet.getUser().getId(),
                                    employee.getLastname()
                        ));
                        }
                    }
                }
            }

            System.out.println("Total présences trouvées après 01/01/2024: " + matchingList.size());
            return matchingList;
        }


    public List<EmpTimesheet> getMatchingEmployeesFromTimesheets() {
        List<Employee> employees = employeeRepository.findAll(); // Récupérer tous les employés (PostgreSQL)
        List<Timesheets> att = timesheetRepository.findAll(); // Récupérer tous les timesheet (MySQL)
        List<EmpTimesheet> matchingList = new ArrayList<>();

        for (Timesheets timesheet : att) {
            String fullName = timesheet.getUser().getFirstname() + " " +  timesheet.getUser().getLastname(); // Concaténer prénom + nom

            for (Employee employee : employees) {
                if (employee.getFirstname().equalsIgnoreCase(fullName)) { // Comparer
                    // Ajouter à la liste
                            matchingList.add(new EmpTimesheet(
                                    employee.getEmpCode(),
                                    timesheet.getPeriod(),
                                    timesheet.getStart(),
                                    timesheet.getEnd(),
                                    timesheet.getUser().getId(),
                                    employee.getLastname()

                            ));
                        }
                    }
                }

            return matchingList;


        }


    public List<EmpTimesheet> getMatchingEmployeesTimesheets() {
        List<Employee> employees = employeeRepository.findAll(); // Récupérer tous les employés (PostgreSQL)
        List<Timesheets> att = timesheetRepository.findAll(); // Récupérer tous les timesheet (MySQL)
        List<EmpTimesheet> matchingList = new ArrayList<>();

        for (Timesheets timesheet : att) {
            String fullName = timesheet.getUser().getFirstname() + " " +  timesheet.getUser().getLastname(); // Concaténer prénom + nom

            for (Employee employee : employees) {
                if (employee.getFirstname().equalsIgnoreCase(fullName)) { // Comparer
                    // Ajouter à la liste
                    matchingList.add(new EmpTimesheet(
                            employee.getEmpCode(),
                            timesheet.getPeriod(),
                            timesheet.getStart(),
                            timesheet.getEnd(),
                            timesheet.getUser().getId(),
                            employee.getLastname()

                    ));
                }
            }
        }

        return matchingList;


    }
    }