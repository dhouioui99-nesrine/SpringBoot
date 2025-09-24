package com.example.IntegrationAPI.Postgres.repository;


import com.example.IntegrationAPI.Postgres.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByEmpCode(String empCode);

    @Query("SELECT e FROM Employee e WHERE e.firstname = :fullName")
    Optional<Employee> findByFullName(@Param("fullName") String fullName);


}