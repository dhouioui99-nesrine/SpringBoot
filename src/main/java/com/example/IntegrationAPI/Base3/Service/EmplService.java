package com.example.IntegrationAPI.Base3.Service;

import com.example.IntegrationAPI.Base3.Repository.DepRepo;
import com.example.IntegrationAPI.Base3.Repository.EmplRepo;
import com.example.IntegrationAPI.Base3.model.Conge;
import com.example.IntegrationAPI.Base3.model.Dep;
import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Postgres.model.Departement;
import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.repository.DepartementRepository;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmplService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmplRepo emplRepository;

    @Autowired
    private DepRepo depRepository;

    public void migrerEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee emp : employees) {
            Empl newEmp = new Empl();

            newEmp.setEmpCode(emp.getEmpCode());
            newEmp.setFirstname(emp.getFirstname());
            newEmp.setLastname(emp.getLastname());
            newEmp.setEmail(emp.getEmail());
            newEmp.setDepartment(emp.getDepartment().getDept_name());

            emplRepository.save(newEmp);
        }
    }

    public List<Empl> getAllEmployees() {

        return  emplRepository.findAll();
    }

    // create
    public Empl save(Empl empl) {
        return emplRepository.save(empl);
    }
    //delete  par empcode
    public void deleteByEmpCode(String empCode) {
        List<Empl> empl = emplRepository.findByEmpCode(empCode);
        if (empl.isEmpty()) {
            throw new RuntimeException("No employee found with empCode: " + empCode);
        }
        for (Empl empls : empl) {
            emplRepository.delete(empls);
        }
    }


    // update  par empcode
    public Empl updateByEmpCode(String empCode, Empl updatedData) {
        Empl empl = emplRepository.findFirstByEmpCode(empCode)
                .orElseThrow(() -> new RuntimeException("employe not found with empCode: " + empCode));

        empl.setEmpCode(updatedData.getEmpCode());
        empl.setFirstname(updatedData.getFirstname());
        empl.setLastname(updatedData.getLastname());
        empl.setDepartment(updatedData.getDepartment());
        empl.setEmail(updatedData.getEmail());

        return emplRepository.save(empl);
    }
    // afficher les conges par empCode
    public List<Empl> getEmployeeById(String empCode) {
        return emplRepository.findByEmpCode(empCode);
    }

}
