package com.example.IntegrationAPI.MySql.Repository;


import com.example.IntegrationAPI.MySql.entity.Users;
import com.example.IntegrationAPI.Postgres.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
  



   // List<Users> findAll();
}