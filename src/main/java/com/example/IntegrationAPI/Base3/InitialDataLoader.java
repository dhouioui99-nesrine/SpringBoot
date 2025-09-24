package com.example.IntegrationAPI.Base3;

import com.example.IntegrationAPI.Base3.Repository.UserRepository;
import com.example.IntegrationAPI.Base3.model.Role;
import com.example.IntegrationAPI.Base3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@gmail.com").isPresent()) {
            System.out.println("L'admin existe déjà, aucune action nécessaire.");
        } else {
            User user = new User();

            user.setPassword(passwordEncoder.encode("123456"));

            user.setFirstname("Administrateur");
            user.setLastname("Global");
            user.setEmail("admin@gmail.com");
            user.setRole(Role.ADMIN);

            try {
                userRepository.save(user);
                System.out.println("Admin ajouté avec succès !");
            } catch (Exception e) {
                throw new Exception("Erreur lors de la création de l'administrateur.", e);
            }

        }
    }
}
