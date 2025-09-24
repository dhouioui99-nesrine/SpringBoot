package com.example.IntegrationAPI.Base3.Controller;


import com.example.IntegrationAPI.Base3.Repository.UserRepository;
import com.example.IntegrationAPI.Base3.config.MailConfig;
import com.example.IntegrationAPI.Base3.model.MessageDTO;
import com.example.IntegrationAPI.Base3.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    MailConfig mailConfig;

    public UserController(UserRepository userRepository, MailConfig mailConfig) {
        this.userRepository = userRepository;
        this.mailConfig = mailConfig;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // Get a list of all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstname(userDetails.getFirstname());
            user.setLastname(userDetails.getLastname());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setMfaEnabled(userDetails.isMfaEnabled());
            user.setSecret(userDetails.getSecret());
            user.setRole(userDetails.getRole());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/verif/{email}")
    public MessageDTO findUserByEmail(@PathVariable String email, HttpServletRequest request) {
        System.out.println("EMAIL "+email);
        User user = userRepository.findByEmail(email).orElse(null);
        String appUrl = request.getScheme() + "://" + request.getServerName()+":4200";
        if (user == null) {
            System.out.println( "We didn't find an account for that e-mail address.");
            return new MessageDTO("We didn't find an account for that e-mail address.");
        } else {
            User userr = user;
            userr.setDateToken(LocalDateTime.now());
            userr.setResetToken(UUID.randomUUID().toString());
            userRepository.save(userr);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("hrportal2023@gmail.com");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Password Reset Request");
            simpleMailMessage.setText("Pou récupérer votre Mot De passe cliquer sur ce Lien :\n" + appUrl
                    + "/resetpwd?token=" + userr.getResetToken());
            System.out.println(userr.getResetToken());
            mailConfig.sendEmail(simpleMailMessage);
            return new MessageDTO("Check you mail");
        }
    }

    @GetMapping("/users/rest/{resetToken}/{password}")
    public MessageDTO findUserByResetToken (@PathVariable String resetToken,@PathVariable String password) {
        System.out.println("Get  User By resetToken..");

        Optional<User> user = userRepository.findByResetToken(resetToken);
        if (!user.isPresent()) {
            System.out.println( "We didn't find an account for that Token");
            return new MessageDTO("We didn't find an account for that Token");
        } else {
            User userr = user.get();
            LocalDateTime tokenCreationDate = userr.getDateToken();

            if (isTokenExpired(tokenCreationDate)) {
                System.out.println("Token expired.");
                return new MessageDTO("Token expired.");
            }
            userr.setPassword(new BCryptPasswordEncoder().encode(password));
            userr.setResetToken(null);
            userr.setDateToken(null);
            userRepository.save(userr);
            return new MessageDTO("Password changed successfully");
        }
    }

    @GetMapping("/verify/rest/{resetToken}")
    public MessageDTO findUserByResetToken (@PathVariable String resetToken){
        Optional<User> user = userRepository.findByResetToken(resetToken);
        if (!user.isPresent()) {
            System.out.println( "We didn't find an account for that Token");
            return new MessageDTO("We didn't find an account for that Token");
        }
        User userr = user.get();
        LocalDateTime tokenCreationDate = userr.getDateToken();

        if (isTokenExpired(tokenCreationDate)) {
            System.out.println("Token expired.");
            return new MessageDTO("Token expired.");
        }else{
            return new MessageDTO("&");
        }
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= 60;
    }
}
