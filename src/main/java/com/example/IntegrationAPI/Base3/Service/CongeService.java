package com.example.IntegrationAPI.Base3.Service;

import com.example.IntegrationAPI.Base3.Repository.CongeRepository;
import com.example.IntegrationAPI.Base3.model.Conge;
import com.example.IntegrationAPI.Dto.EmployeeAtt;
import com.example.IntegrationAPI.MySql.Repository.AttendanceRepository;
import com.example.IntegrationAPI.Postgres.model.Leave;
import com.example.IntegrationAPI.Postgres.repository.LeaveRepository;
import com.example.IntegrationAPI.Service.EmployeeAttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CongeService {


    @Autowired
    private LeaveRepository biotimeRepo;

    @Autowired
    private AttendanceRepository easyRepo;

    @Autowired
    private CongeRepository congeRepo;
    @Autowired
    private EmployeeAttService employeeAttService;

    public void transformAndSaveCongeData() {
        // Biotime ➜ Conge
        List<Leave> biotimeLeaves = biotimeRepo.findAll();
        for (Leave leave : biotimeLeaves) {
            Conge conge = new Conge();
            conge.setEmpCode(leave.getWorkflow().getEmployee().getEmpCode());
            conge.setApprover(leave.getWorkflow().getApprover());
            conge.setStart(leave.getStart());
            conge.setEnd(leave.getEnd());
            conge.setReason(leave.getReason());
            conge.setDay(leave.getDay());
            conge.setFirstname(leave.getWorkflow().getEmployee().getFirstname());
            conge.setPaycode(leave.getPaycode().getName());
            conge.setSource("Biotime");

            congeRepo.save(conge);
        }

        // EasyProject ➜ Conge

        List<EmployeeAtt> attendances = employeeAttService.getMatchingEmployees(); // <- appel correct à ton service ou repo
        for (EmployeeAtt att : attendances) {
            Conge conge = new Conge();
            conge.setEmpCode(att.getEmpCode());  // Ajoute ce champ dans EmployeeAtt si ce n'est pas encore fait
            conge.setStart(att.getDeparture());
            conge.setEnd(att.getArrival());
            conge.setReason(att.getAttendance());
            conge.setPaycode(att.getDescription());
            conge.setFirstname(att.getLastname());
            conge.setSource("EasyProject");

            congeRepo.save(conge);
        }
    }


    public List<Conge> getAllEmployees() {

        return  congeRepo.findAll();


    }
    // afficher les conges par empCode
    public List<Conge> getEmployeeById(String empCode) {
        return congeRepo.findByEmpCode(empCode);
    }


    // create
    public Conge save(Conge conge) {
        return congeRepo.save(conge);
    }




    //delete  par empcode
    public void deleteByEmpCode(String empCode) {
        List<Conge> conge = congeRepo.findByEmpCode(empCode);
        if (conge.isEmpty()) {
            throw new RuntimeException("No employee found with empCode: " + empCode);
        }
        for (Conge conges : conge) {
            congeRepo.delete(conges);
        }
    }


    // update  par empcode
    public Conge updateByEmpCode(String empCode, Conge updatedData) {
        Conge conge = congeRepo.findFirstByEmpCode(empCode)
                .orElseThrow(() -> new RuntimeException("Conge not found with empCode: " + empCode));

        conge.setStart(updatedData.getStart());
        conge.setEnd(updatedData.getEnd());
        conge.setReason(updatedData.getReason());
        conge.setDay(updatedData.getDay());
        conge.setPaycode(updatedData.getPaycode());
        conge.setSource(updatedData.getSource());
        conge.setApprover(updatedData.getApprover());

        return congeRepo.save(conge);
    }
    public long getTeletravailCountByYear(int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);
        return congeRepo.countByPaycodeAndYear("teletravail", startOfYear, endOfYear);
    }
    public long getOrdreCountByYear(int year) {
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);
        return congeRepo.countByPaycodeAndYear("Congé annuel", startOfYear, endOfYear);
    }
}
