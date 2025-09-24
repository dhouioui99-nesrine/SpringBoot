
package com.example.IntegrationAPI.Postgres.controller;

import com.example.IntegrationAPI.Postgres.service.iclock_transactionService;
import com.example.IntegrationAPI.Postgres.service.iclock_transactionService.PointageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/valid-transactions")

public class iclock_transactionController {

    private final iclock_transactionService iclockTransactionService;

    @Autowired
    public iclock_transactionController(iclock_transactionService iclockTransactionService) {
        this.iclockTransactionService = iclockTransactionService;
    }

    // API pour les transactions valides

    public List<PointageResult> getValidTransactions() {
        return iclockTransactionService.getPremierEtDernierPointageParJour();
    }

    // API pour les transactions invalides


    @GetMapping("/{empCode}")
    public ResponseEntity<List<PointageResult>> getPointageByEmpCode(@PathVariable String empCode) {
        List<PointageResult> pointages = iclockTransactionService.getPointageByEmpCode( empCode);
        if (pointages.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(pointages);
        }
    }
}


