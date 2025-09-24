package com.example.IntegrationAPI.MySql.Repository;

import com.example.IntegrationAPI.MySql.entity.issue_statuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Issue_statusesRepository extends JpaRepository<issue_statuses, Long> {
}
