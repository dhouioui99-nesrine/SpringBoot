package com.example.IntegrationAPI.MySql.Repository;

import com.example.IntegrationAPI.MySql.entity.TimeUser;
import com.example.IntegrationAPI.MySql.entity.Timesheets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Repository
public interface TimeUserRepository extends JpaRepository<TimeUser, Long> {




}