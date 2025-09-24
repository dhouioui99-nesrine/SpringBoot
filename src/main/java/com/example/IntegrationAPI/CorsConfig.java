package com.example.IntegrationAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Autoriser toutes les URL
                .allowedOrigins("http://localhost:4200")  // Autoriser les requêtes de localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Les méthodes HTTP autorisées
                .allowedHeaders("*");  // Autoriser tous les headers
    }
}
