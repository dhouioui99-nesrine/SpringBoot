package com.example.IntegrationAPI.Base3.Repository;

import com.example.IntegrationAPI.Base3.model.issue_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface issue_statRepository extends JpaRepository<issue_stat, Long> {
}
