package com.example.IntegrationAPI.Postgres.repository;



import com.example.IntegrationAPI.Postgres.model.iclock_transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;


public interface iclock_transactionRepository extends JpaRepository<iclock_transaction, Integer> {


    // Récupérer toutes les transactions d'un employé avant une date donnée
    @Query("SELECT t FROM iclock_transaction t WHERE t.empId = :empId AND t.punch_time < :dateLimit")
    List<iclock_transaction> findByEmpIdAndBeforeDate(@Param("empId") Integer empId, @Param("dateLimit") ZonedDateTime dateLimit);
}
