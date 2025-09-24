package com.example.IntegrationAPI.MySql.Controller;

import com.example.IntegrationAPI.MySql.Service.UsersService;
import com.example.IntegrationAPI.MySql.entity.Users;
import org.springframework.web.bind.annotation.* ;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService ) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }
}
