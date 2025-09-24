package com.example.IntegrationAPI.Postgres.service;




import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.model.iclock_transaction;
import com.example.IntegrationAPI.Postgres.repository.EmployeeRepository;
import com.example.IntegrationAPI.Postgres.repository.iclock_transactionRepository;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class iclock_transactionService {

    private final iclock_transactionRepository iclockTransactionRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public iclock_transactionService(iclock_transactionRepository iclockTransactionRepository,
                                     EmployeeRepository employeeRepository) {
        this.iclockTransactionRepository = iclockTransactionRepository;
        this.employeeRepository = employeeRepository;
    }

    // Classe interne pour inclure nom et département
    public static class PointageResult {
        private Integer empId;
        private String empCode;

        private String empName;

        private String deptName;
        private String date;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private ZonedDateTime premierPointage;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private ZonedDateTime dernierPointage;

        public PointageResult(Integer empId, String empCode,String empName, String deptName, String date, ZonedDateTime premierPointage, ZonedDateTime dernierPointage) {
            this.empId = empId;
            this.empCode=empCode;
            this.empName = empName;
            this.deptName = deptName;
            this.date = date;

            this.premierPointage = premierPointage;
            this.dernierPointage = dernierPointage;
        }

        public Integer getEmpId() { return empId; }

        public String getEmpCode() {
            return empCode;
        }

        public String getEmpName() { return empName; }
        public String getDeptName() { return deptName; }
        public String getDate() { return date; }

        public ZonedDateTime getPremierPointage() { return premierPointage.plusHours(1); }
        public ZonedDateTime getDernierPointage() { return dernierPointage.plusHours(1); }
    }

    public List<PointageResult> getPremierEtDernierPointageParJour() {
        List<iclock_transaction> transactions = iclockTransactionRepository.findAll();

        List<iclock_transaction> validTransactions = transactions.stream()
                .filter(tx -> tx.getEmpId() != null && tx.getPunch_time() != null)
                .collect(Collectors.toList());

        Map<Integer, Map<String, List<iclock_transaction>>> groupedByEmpAndDate = validTransactions.stream()
                .collect(Collectors.groupingBy(
                        iclock_transaction::getEmpId,
                        Collectors.groupingBy(tx -> tx.getPunch_time().toLocalDate().toString())
                ));

        List<PointageResult> pointageResults = new ArrayList<>();
// Liste des départements à exclure
        List<String> excludedDepartments = Arrays.asList("OTH", "Appli_Groupe", "Infra_Groupe", "Innov_Groupe", "stagiaire");

        for (Map.Entry<Integer, Map<String, List<iclock_transaction>>> empEntry : groupedByEmpAndDate.entrySet()) {
            Integer Id = empEntry.getKey();

            // Récupérer les informations de l'employé
            Employee employee = employeeRepository.findById(Long.valueOf(Id)).orElse(null);
            String emp_code = (employee != null) ? employee.getEmpCode() : "Inconnu";
            String empName = (employee != null) ? employee.getFirstname() : "Inconnu";
            String deptName = (employee != null && employee.getDepartment() != null) ? employee.getDepartment().getDept_name() : "Inconnu";

            // Si le département de l'employé est dans la liste des départements exclus, on passe à l'employé suivant
            if (excludedDepartments.contains(deptName)) {
                continue;
            }

            for (Map.Entry<String, List<iclock_transaction>> dateEntry : empEntry.getValue().entrySet()) {
                String date = dateEntry.getKey();
                List<iclock_transaction> transactionsForDay = dateEntry.getValue();

                ZonedDateTime premierPointage = transactionsForDay.stream()
                        .min(Comparator.comparing(iclock_transaction::getPunch_time))
                        .map(iclock_transaction::getPunch_time)
                        .orElse(null);

                ZonedDateTime dernierPointage = transactionsForDay.stream()
                        .max(Comparator.comparing(iclock_transaction::getPunch_time))
                        .map(iclock_transaction::getPunch_time)
                        .orElse(null);

                // Ajout des résultats à la liste si le département n'est pas exclu
                pointageResults.add(new PointageResult(Id, emp_code, empName, deptName, date, premierPointage, dernierPointage));
            }
        }

        return pointageResults;
    }




 public List<PointageResult> getPointageByEmpCode(String empCode) {
        return getPremierEtDernierPointageParJour().stream()
                // Filtrer les résultats par empCode
                .filter(pointage -> pointage.getEmpCode().equals(empCode))
                // Filtrer pour ne garder que les dates de 2024
                .filter(pointage -> pointage.getDate().startsWith("2024"))
                // Trier les résultats par date, les dates de 2024 seront en premier
                .sorted(Comparator.comparing(PointageResult::getDate))
                .collect(Collectors.toList());
    }

/*
    public List<PointageResult> getPointageByEmpCode(String empCode) {
        return getPremierEtDernierPointageParJour().stream()
                .filter(pointage -> pointage.getEmpCode().equals(empCode))
                .collect(Collectors.toList());
    }

*/

}