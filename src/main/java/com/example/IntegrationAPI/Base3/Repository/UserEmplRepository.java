package com.example.IntegrationAPI.Base3.Repository;

import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Base3.model.UserEmpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmplRepository extends JpaRepository<UserEmpl, Long> {
}
