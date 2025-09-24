package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Repository.ProjetRepository;
import com.example.IntegrationAPI.Base3.Service.ProjectService;
import com.example.IntegrationAPI.Base3.model.Empl;
import com.example.IntegrationAPI.Base3.model.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/B3/projet")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjetController {
    @Autowired
    private ProjectService transformationService;
    @Autowired
    private ProjetRepository projetRepository;
    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.migrerEmployees();
        return ResponseEntity.ok("Transformation terminée.");
    }
    // afficher ALLL
    @GetMapping
    public List<Projet> getAll() {
        return transformationService.getAllEmployees();
    }
    // Ajouter
    @PostMapping
    public Projet create(@RequestBody Projet empl) {
        return transformationService.save(empl);
    }

    // afficher par id
    @GetMapping("/{id}")
    public ResponseEntity<Projet> getCongeById(@PathVariable("id") Long id) {
        Projet empl = transformationService.getEmployeeById(id);

        return ResponseEntity.ok(empl);
    }

    //Modifier
    @PutMapping("/{id}")
    public Projet updateByEmpCode(@PathVariable Long id, @RequestBody Projet updatedData) {
        return transformationService.updateById(id, updatedData);
    }
//supprimer


    @DeleteMapping("/{id}")
    public void deleteByEmpCode(@PathVariable Long id) {
        transformationService.deleteById(id);
    }

    @GetMapping("/count")
    public long getEmployeeCount() {
        return projetRepository.count();
    }
}

