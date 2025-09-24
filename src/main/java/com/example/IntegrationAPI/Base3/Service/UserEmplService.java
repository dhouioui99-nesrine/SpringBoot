package com.example.IntegrationAPI.Base3.Service;

import com.example.IntegrationAPI.Base3.Repository.DepRepo;
import com.example.IntegrationAPI.Base3.Repository.EmplRepo;
import com.example.IntegrationAPI.Base3.Repository.UserEmplRepository;
import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Base3.model.UserEmpl;
import com.example.IntegrationAPI.MySql.Repository.UsersRepository;
import com.example.IntegrationAPI.MySql.entity.Users;
import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserEmplService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserEmplRepository userEmplRepository;

    @Autowired
    private UsersRepository usersRepository;

    public void transfererDonnees() {
        List<Employee> employees = employeeRepository.findAll();
        List<Users> users = usersRepository.findAll();

        for (Employee emp : employees) {
            for (Users use : users) {

                UserEmpl ue = new UserEmpl();
                ue.setEmpCode(emp.getEmpCode());
                ue.setLastname(emp.getLastname());
                ue.setDepartment(emp.getDepartment().getDept_name());
                ue.setUserId(use.getId());

                userEmplRepository.save(ue);
            }
        }
    }


}


