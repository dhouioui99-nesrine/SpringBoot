package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Service.EmplService;
import com.example.IntegrationAPI.Base3.Service.Issue_statService;
import com.example.IntegrationAPI.Base3.model.Empl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/B3/statut")
@CrossOrigin(origins = "http://localhost:4200")
public class Issue_statController {
    @Autowired
    private Issue_statService transformationService;

    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.migrerEmployees();
        return ResponseEntity.ok("Transformation terminée.");
    }

}
