package com.example.IntegrationAPI.Base3.Repository;

import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Base3.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
        Optional<Projet> findFirstById(Long id);
    Optional<Projet> findById(Long id);
}
