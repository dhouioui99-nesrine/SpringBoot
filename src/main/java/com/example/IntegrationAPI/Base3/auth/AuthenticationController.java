package com.example.IntegrationAPI.Base3.auth;

import com.example.IntegrationAPI.Base3.Repository.EmplRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")

public class AuthenticationController {

    private final AuthenticationService service;
    private final EmplRepo repository;
    public AuthenticationController(AuthenticationService service , EmplRepo repository) {
        this.service = service;
        this.repository=repository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        var response = service.register(request);
        if (request.isMfaEnabled()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(
            @RequestBody VerificationRequest verificationRequest
    ) {
        return ResponseEntity.ok(service.verifyCode(verificationRequest));
    }
    @GetMapping("/user")
    public ResponseEntity<UserDetails> getCurrentUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ) {
        if(token == null){
            return ResponseEntity.status(200).body(null);
        }


        // Remove "Bearer " prefix if present
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        UserDetails userDetails = service.getUserDetailsFromToken(jwtToken);
        System.out.println(userDetails);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.status(401).body(null); // Unauthorized
        }

    }
    @GetMapping("/check-empcode")
    public ResponseEntity<Boolean> checkEmpCodeExists(@RequestParam String empCode) {
        boolean exists = repository.existsByEmpCode(empCode);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = repository.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("userId is required");
        }

        service.logout(email);

        return ResponseEntity.ok("Logout successful");
    }

}