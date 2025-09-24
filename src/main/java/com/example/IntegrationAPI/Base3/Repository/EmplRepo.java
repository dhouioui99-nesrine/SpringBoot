package com.example.IntegrationAPI.Base3.Repository;


import com.example.IntegrationAPI.Base3.model.Conge;
import com.example.IntegrationAPI.Base3.model.Empl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmplRepo extends JpaRepository<Empl, Long> {


    List<Empl> findByEmpCode(String empCode);
    Optional<Empl> findFirstByEmpCode(String empCode);
    boolean existsByEmpCode(String empCode);
    boolean existsByEmail (String email) ;
}
