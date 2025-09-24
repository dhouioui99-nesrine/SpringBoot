package com.example.IntegrationAPI.Base3.Repository;


import com.example.IntegrationAPI.Base3.model.Dep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DepRepo  extends JpaRepository<Dep, Long> {
    Dep findByName(String name);
}

