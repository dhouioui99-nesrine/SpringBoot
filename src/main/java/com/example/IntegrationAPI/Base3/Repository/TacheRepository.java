package com.example.IntegrationAPI.Base3.Repository;

import com.example.IntegrationAPI.Base3.model.Projet;
import com.example.IntegrationAPI.Base3.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {

    Optional<Tache> findFirstById(Long id);
    Optional<Tache> findById(Long id);
    @Query("SELECT t FROM Tache t WHERE t.projet.id = :projetId")
    List<Tache> findByProjetId(@Param("projetId") Long projetId);
}
