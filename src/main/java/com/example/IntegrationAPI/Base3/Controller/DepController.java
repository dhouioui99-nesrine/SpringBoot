package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Service.CongeService;
import com.example.IntegrationAPI.Base3.Service.DepService;
import com.example.IntegrationAPI.Base3.model.Dep;
import com.example.IntegrationAPI.Base3.model.Empl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dep")
@CrossOrigin(origins = "http://localhost:4200")
public class DepController {
    @Autowired
    private DepService transformationService;

    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.migrerDepartements();
        return ResponseEntity.ok("Transformation terminée.");
    }
    // afficher ALLL
    @PreAuthorize("permitAll()")
    @GetMapping
    public List<Dep> getAll() {
        return transformationService.getAllEmployees();
    }
}
