package com.example.IntegrationAPI.Base3.Repository;

import com.example.IntegrationAPI.Base3.model.Conge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface CongeRepository extends JpaRepository<Conge, Long> {


    List<Conge> findByEmpCode(String empCode);

    Optional<Conge> findFirstByEmpCode(String empCode);

    @Query("SELECT COUNT(c) FROM Conge c WHERE YEAR(c.start) = :year")
    long countByYear(@Param("year") int year);

    @Query("SELECT COUNT(c) FROM Conge c " +
            "WHERE c.paycode = :paycode " +
            "AND c.start BETWEEN :startOfYear AND :endOfYear")
    long countByPaycodeAndYear(@Param("paycode") String paycode,
                               @Param("startOfYear") LocalDate startOfYear,
                               @Param("endOfYear") LocalDate endOfYear);

}