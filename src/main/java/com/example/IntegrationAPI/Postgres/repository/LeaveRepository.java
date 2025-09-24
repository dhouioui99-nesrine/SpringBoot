package com.example.IntegrationAPI.Postgres.repository;

import com.example.IntegrationAPI.Postgres.model.Leave;
import com.example.IntegrationAPI.Postgres.model.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Repository
public interface LeaveRepository  extends JpaRepository<Leave, Workflow> {

  //  Leave findByWorkflow(Workflow workflow);


    @Query("SELECT l FROM Leave l WHERE l.workflow.employee.empCode = :empCode")
    List<Leave> findLeaveByEmpCode(@Param("empCode") String empCode);
}
