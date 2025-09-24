package com.example.IntegrationAPI.Postgres.repository;

import com.example.IntegrationAPI.Postgres.model.Departement;
import com.example.IntegrationAPI.Postgres.model.Employee;
import com.example.IntegrationAPI.Postgres.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Repository
public interface WorkflowRepository  extends JpaRepository<Workflow, Long> {

    List<Workflow> findByEmployee_EmpCode(String empCode);
}
