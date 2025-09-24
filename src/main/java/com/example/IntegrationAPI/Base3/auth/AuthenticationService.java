package com.example.IntegrationAPI.Base3.auth;


import com.example.IntegrationAPI.Base3.Repository.EmplRepo;
import com.example.IntegrationAPI.Base3.Repository.UserRepository;
import com.example.IntegrationAPI.Base3.config.JwtService;
import com.example.IntegrationAPI.Base3.model.Role;
import com.example.IntegrationAPI.Base3.model.User;
import com.example.IntegrationAPI.Base3.tfa.TwoFactorAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.GrantedAuthority;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtUtil;
    private final UserDetailsService userDetailsService;
    private final TwoFactorAuthenticationService tfaService ;

    private final EmplRepo emplRepository;
    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, JwtService jwtUtil, UserDetailsService userDetailsService, TwoFactorAuthenticationService tfaService , EmplRepo emplRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tfaService=tfaService;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.emplRepository=emplRepository;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        // 1. Vérifie si le code employé existe dans la table Empl
        if (!emplRepository.existsByEmpCode(request.getEmpCode())) {
            throw new IllegalArgumentException(
                    "Le code employé " + request.getEmpCode() + " n'existe pas dans les employés enregistrés.");
        }

        // 2. Vérifie si un utilisateur avec le même code existe déjà
        if (repository.existsByEmpCode(request.getEmpCode())) {
            throw new IllegalArgumentException(
                    "L’utilisateur avec le code " + request.getEmpCode() + " existe déjà.");
        }

        // 3. Création de l'utilisateur
        User user = new User();
        user.setEmpCode(request.getEmpCode());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setMfaEnabled(request.isMfaEnabled());

        if (request.isMfaEnabled()) {
            user.setSecret(tfaService.generateNewSecret());
        }

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtToken);
        response.setRefreshToken(refreshToken);
        response.setMfaEnabled(user.isMfaEnabled());
        response.setSecretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()));

        return response;
    }
    public UserDetails getUserDetailsFromToken(String token) {
        String username = jwtUtil.extractUsername(token);
        System.out.println(username);
        if (username != null) {
            return userDetailsService.loadUserByUsername(username);
        }
        return null; // Token is invalid or user not found
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getEmail());

        // Vérifier identifiants
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Récupérer l'utilisateur
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        AuthenticationResponse response = new AuthenticationResponse();

        // Remplir informations utilisateur
        response.setEmpCode(user.getEmpCode());
        response.setLastname(user.getLastname());
        if (user.getRole() != null) {
            response.setRoles(List.of(user.getRole().name()));
        } else {
            response.setRoles(Collections.emptyList());
        }

        // Gestion MFA
        if (user.isMfaEnabled()) {
            response.setAccessToken(""); // peut rester vide
            response.setRefreshToken("");
            response.setMfaEnabled(true);
        } else {
            // JWT token
            String jwtToken = jwtService.generateToken(user);
            response.setAccessToken(jwtToken);
            response.setRefreshToken(""); // ou gérer refresh token
            response.setMfaEnabled(false);
        }

        return response;
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                AuthenticationResponse authResponse = new AuthenticationResponse();
                authResponse.setAccessToken(accessToken);
                authResponse.setRefreshToken(refreshToken);
                authResponse.setMfaEnabled(false);
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public AuthenticationResponse verifyCode(VerificationRequest verificationRequest) {
        // 1. Récupère l’utilisateur
        User user = repository.findByEmail(verificationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "No user found with " + verificationRequest.getEmail()));

        // 2. Parse le code saisi en entier
        String code;
        try {
            code = verificationRequest.getCode().trim();

        } catch (NumberFormatException ex) {
            throw new BadCredentialsException("Code 2FA invalide");
        }

        // 3. Vérifie le code TOTP
        //    Ici on suppose que ton tfaService expose une méthode verifyCode
        //    qui renvoie true si le code est valide dans la fenêtre temporelle.
        boolean valid = tfaService.isOtpValid(user.getSecret(), code);
        if (!valid) {
            throw new BadCredentialsException("Code 2FA incorrect ou expiré");
        }

        // 4. Si valide, génère le JWT
        String jwtToken = jwtService.generateToken(user);

        // 5. Prépare et retourne la réponse
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setAccessToken(jwtToken);
        authResponse.setMfaEnabled(user.isMfaEnabled());
        return authResponse;
    }


    public boolean empCodeExists(String empCode) {
        return emplRepository.existsByEmpCode(empCode);
    }

    public boolean emailExists(String email) {
        return emplRepository.existsByEmail(email);
    }
    public void logout(String email) {
        // Exemple : invalidation côté serveur
        // refreshTokenRepository.deleteByUserId(userId);

        System.out.println("User " + email + " logged out.");
    }
}
