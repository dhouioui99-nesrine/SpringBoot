package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Repository.TacheRepository;
import com.example.IntegrationAPI.Base3.Service.TacheService;
import com.example.IntegrationAPI.Base3.Service.TicketService;
import com.example.IntegrationAPI.Base3.model.Projet;
import com.example.IntegrationAPI.Base3.model.Tache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/B3/tache")
@CrossOrigin(origins = "http://localhost:4200")
public class TacheController {

    @Autowired
    private TacheService transformationService;
    @Autowired
    private TacheRepository tacheRepository;
    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.migrerEmployees();
        return ResponseEntity.ok("Transformation terminée.");
    }
    // afficher ALLL
    @GetMapping
    public List<Tache> getAll() {
        return transformationService.getAllEmployees();
    }
    // Ajouter
    @PostMapping
    public Tache create(@RequestBody Tache empl) {
        return transformationService.save(empl);
    }

    // afficher par id
    @GetMapping("/{id}")
    public ResponseEntity<Tache> getCongeById(@PathVariable("id") Long id) {
        Tache empl = transformationService.getEmployeeById(id);

        return ResponseEntity.ok(empl);
    }

    //Modifier
    @PutMapping("/{id}")
    public Tache updateByEmpCode(@PathVariable Long id, @RequestBody Tache updatedData) {
        return transformationService.updateById(id, updatedData);
    }
//supprimer


    @DeleteMapping("/{id}")
    public void deleteByEmpCode(@PathVariable Long id) {
        transformationService.deleteById(id);
    }


    @GetMapping("/projet/{id}")
    public ResponseEntity<List<Tache>> getTachesByProjet(@PathVariable Long id) {
        List<Tache> taches = transformationService.getTachesByProjetId(id);
        return ResponseEntity.ok(taches);
    }


    @PostMapping("/ajouter/{projetId}")
    public ResponseEntity<?> ajouterTacheAuProjet(@PathVariable Long projetId, @RequestBody Tache tache) {
        try {
            Tache savedTache = transformationService.ajouterTacheAuProjet(projetId, tache);
            return ResponseEntity.ok(savedTache);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Erreur : " + e.getMessage());
        }
    }
    @GetMapping("/count")
    public long getEmployeeCount() {
        return tacheRepository.count();
    }
}

