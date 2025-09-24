package com.example.IntegrationAPI.MySql.Repository;

import com.example.IntegrationAPI.MySql.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
