package com.example.IntegrationAPI.Service;

import com.example.IntegrationAPI.Dto.EmployeeAtt;
import com.example.IntegrationAPI.Dto.EmployeeUser;
import com.example.IntegrationAPI.MySql.Repository.AttendanceRepository;
import com.example.IntegrationAPI.MySql.Repository.UsersRepository;
import com.example.IntegrationAPI.MySql.entity.Attendance;
import com.example.IntegrationAPI.MySql.entity.Users;
import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeAttService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public EmployeeAttService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }
    public List<Object> getAllEmployees() {
        List<Employee> employeesPostgres = employeeRepository.findAll();
        List<Attendance> employeesMySQL = attendanceRepository.findAll();

        List<Object> allEmployees = new ArrayList<>();
        allEmployees.addAll(employeesPostgres);
        allEmployees.addAll(employeesMySQL);

        return allEmployees;
    }
    public List<EmployeeAtt> getMatchingEmployees() {
        List<Employee> employees = employeeRepository.findAll(); // Récupérer tous les employés (PostgreSQL)
        List<Attendance> att = attendanceRepository.findAll(); // Récupérer tous les utilisateurs (MySQL)
        List<EmployeeAtt> matchingList = new ArrayList<>();

        for (Attendance user : att) {
            String fullName = user.getUser().getFirstname() + " " +  user.getUser().getLastname(); // Concaténer prénom + nom

            for (Employee employee : employees) {
                if (employee.getFirstname().equalsIgnoreCase(fullName)) { // Comparer
                    // Ajouter à la liste
                    matchingList.add(new EmployeeAtt(

                            employee.getEmpCode(),
                            user.getIdAct().getName(),
                            user.getArrival(),
                            user.getDeparture(),
                            user.getDescription(),
                            employee.getLastname(),
                            employee.getDepartment().getDept_name()



                    ));
                }
            }
        }

        return matchingList;
    }

    public List<EmployeeAtt> getAttendancesByEmpCode(String empCode) {
        System.out.println("Recherche des présences pour empCode: " + empCode);

        List<Employee> employees = employeeRepository.findAll(); // Récupérer tous les employés (PostgreSQL)
        List<Attendance> attendances = attendanceRepository.findAll(); // Récupérer toutes les présences (MySQL)
        List<EmployeeAtt> matchingList = new ArrayList<>();

        // Définir la date minimale
       // LocalDateTime minDate = LocalDateTime.of(2024, 1, 1, 0, 0);

        for (Attendance attendance : attendances) {
            // Vérifier si la date d’arrivée est après le 01/01/2024
            if (attendance.getArrival() != null
                   // && attendance.getArrival().isAfter(minDate)
            ) {
                String fullName = attendance.getUser().getFirstname().trim() + " " + attendance.getUser().getLastname().trim();

                for (Employee employee : employees) {
                    if (employee.getEmpCode().equals(empCode) &&
                            employee.getLastname().trim().equalsIgnoreCase(fullName)) {

                        System.out.println("Match trouvé pour empCode: " + empCode);

                        matchingList.add(new EmployeeAtt(
                                employee.getEmpCode(),
                                attendance.getIdAct().getName(),
                                attendance.getArrival(),
                                attendance.getDeparture(),
                                attendance.getDescription(),
                                employee.getLastname(),
                                employee.getDepartment().getDept_name()
                        ));
                    }
                }
            }
        }

        System.out.println("Total présences trouvées après 01/01/2024: " + matchingList.size());
        return matchingList;
    }
}
