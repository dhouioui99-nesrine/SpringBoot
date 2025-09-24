package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Repository.EmplRepo;
import com.example.IntegrationAPI.Base3.Service.DepService;
import com.example.IntegrationAPI.Base3.Service.EmplService;
import com.example.IntegrationAPI.Base3.model.Conge;
import com.example.IntegrationAPI.Base3.model.Empl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empl")
@CrossOrigin(origins = "http://localhost:4200")
public class EmplController {
    @Autowired
    private EmplService transformationService;
    @Autowired
    private EmplRepo emplRepo;
    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.migrerEmployees();
        return ResponseEntity.ok("Transformation terminée.");
    }
    // afficher ALLL
    @GetMapping
    public List<Empl> getAll() {
        return transformationService.getAllEmployees();
    }
    // Ajouter
    @PostMapping
    public Empl create(@RequestBody Empl empl) {
        return transformationService.save(empl);
    }

    // afficher par empCode
    @GetMapping("/{empCode}")
    public ResponseEntity<List<Empl>> getCongeByCode(@PathVariable("empCode") String empCode) {
        List<Empl> empl = transformationService.getEmployeeById(empCode);

        if (empl.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(empl);
    }

    //Modifier
    @PutMapping("/{empCode}")
    public Empl updateByEmpCode(@PathVariable String empCode, @RequestBody Empl updatedData) {
        return transformationService.updateByEmpCode(empCode, updatedData);
    }
//supprimer


    @DeleteMapping("/{empCode}")
    public void deleteByEmpCode(@PathVariable String empCode) {
        transformationService.deleteByEmpCode(empCode);
    }

    @GetMapping("/count")
    public long getEmployeeCount() {
        return emplRepo.count();
    }
}

