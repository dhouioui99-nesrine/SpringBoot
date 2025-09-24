package com.example.IntegrationAPI.Postgres.repository;

import com.example.IntegrationAPI.Postgres.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface DepartementRepository  extends JpaRepository<Departement, Long> {
}
