package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Repository.CongeRepository;
import com.example.IntegrationAPI.Base3.Service.CongeService;
import com.example.IntegrationAPI.Base3.model.Conge;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import java.util.List;



@RestController
@RequestMapping("/api/B3")
@CrossOrigin(origins = "http://localhost:4200")
public class CongeController {

    @Autowired
    private CongeService transformationService;
    @Autowired
    private CongeRepository congeRepository;

    @GetMapping("/count")
    public long getEmployeeCount() {
        return congeRepository.count();
    }
    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.transformAndSaveCongeData();
        return ResponseEntity.ok("Transformation terminée.");
    }


    // afficher ALLL
    @GetMapping
    public List<Conge> getAll() {
        return transformationService.getAllEmployees();
    }
    @GetMapping("/code/{empCode}")
    public ResponseEntity<List<Conge>> getEmployeesByCode(@PathVariable("empCode") String empCode) {
        List<Conge> employees = transformationService.getEmployeeById(empCode);

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(employees);
    }

    // Ajouter
    @PostMapping
    public Conge create(@RequestBody Conge conge) {
        return transformationService.save(conge);
    }

    // afficher par empCode
    @GetMapping("/{empCode}")
    public ResponseEntity<List<Conge>> getCongeByCode(@PathVariable("empCode") String empCode) {
        List<Conge> conge = transformationService.getEmployeeById(empCode);

        if (conge.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(conge);
    }

    //Modifier
    @PutMapping("/{empCode}")
    public Conge updateByEmpCode(@PathVariable String empCode, @RequestBody Conge updatedData) {
        return transformationService.updateByEmpCode(empCode, updatedData);
    }
//supprimer


    @DeleteMapping("/{empCode}")
    public void deleteByEmpCode(@PathVariable String empCode) {
        transformationService.deleteByEmpCode(empCode);
    }

    @GetMapping("/count/{year}")
    public long getCongeCountByYear(@PathVariable int year) {
        return congeRepository.countByYear(year);
    }
    @GetMapping("/count/teletravail/{year}")
    public long getTeletravailCongeCountByYear(@PathVariable int year) {
        return transformationService.getTeletravailCountByYear(year);
    }
    @GetMapping("/count/ordre/{year}")
    public long getordreCongeCountByYear(@PathVariable int year) {
        return transformationService.getOrdreCountByYear(year);
    }
}