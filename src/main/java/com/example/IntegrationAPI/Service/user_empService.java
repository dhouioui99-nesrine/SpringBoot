package com.example.IntegrationAPI.Service;

import com.example.IntegrationAPI.Dto.EmployeeUser;
import com.example.IntegrationAPI.MySql.Repository.UsersRepository;
import com.example.IntegrationAPI.MySql.entity.Users;
import com.example.IntegrationAPI.Postgres.model.Departement;
import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class user_empService {

    private final EmployeeRepository employeeRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public user_empService(EmployeeRepository employeeRepository,UsersRepository usersRepository) {
        this.employeeRepository = employeeRepository;
        this.usersRepository = usersRepository;
    }

    public List<Object> getAllEmployees() {
        List<Employee> employeesPostgres = employeeRepository.findAll();
        List<Users> employeesMySQL = usersRepository.findAll();

        List<Object> allEmployees = new ArrayList<>();
        allEmployees.addAll(employeesPostgres);
        allEmployees.addAll(employeesMySQL);

        return allEmployees;
    }

/*
public List<Employee> getMatchingEmployees() {
        List<Users> users = usersRepository.findAll();
        List<Employee> matchingEmployees = new ArrayList<>();

        for (Users user : users) {
            String fullName = user.getFirstname() + " " + user.getLastname();
            employeeRepository.findByFullName(fullName).ifPresent(matchingEmployees::add);
        }

        return matchingEmployees;
    }
 */

    public List<EmployeeUser> getMatchingEmployees() {
        List<Employee> employees = employeeRepository.findAll(); // Récupérer tous les employés (PostgreSQL)
        List<Users> users = usersRepository.findAll(); // Récupérer tous les utilisateurs (MySQL)
        List<EmployeeUser> matchingList = new ArrayList<>();

        for (Users user : users) {
            String fullName = user.getFirstname() + " " + user.getLastname(); // Concaténer prénom + nom

            for (Employee employee : employees) {
                if (employee.getFirstname().equalsIgnoreCase(fullName)) { // Comparer
                    // Ajouter à la liste
                    matchingList.add(new EmployeeUser(
                            user.getId(),
                            employee.getEmpCode(),
                            employee.getLastname(),
                           employee.getDepartment().getDept_name()



                    ));
                }
            }
        }

        return matchingList;
    }

}