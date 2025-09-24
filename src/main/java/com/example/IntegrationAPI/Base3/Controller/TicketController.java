package com.example.IntegrationAPI.Base3.Controller;

import com.example.IntegrationAPI.Base3.Service.Issue_statService;
import com.example.IntegrationAPI.Base3.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/B3/tracker")
public class TicketController {
    @Autowired
    private TicketService transformationService;

    @PostMapping("/transform")
    public ResponseEntity<String> transform() {
        transformationService.migrerEmployees();
        return ResponseEntity.ok("Transformation terminée.");
    }



}
