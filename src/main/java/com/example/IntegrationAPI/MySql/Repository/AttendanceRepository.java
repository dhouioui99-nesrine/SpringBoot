package com.example.IntegrationAPI.MySql.Repository;


import com.example.IntegrationAPI.MySql.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {




}

