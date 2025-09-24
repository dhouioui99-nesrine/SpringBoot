package com.example.IntegrationAPI.MySql.Service;


import com.example.IntegrationAPI.MySql.Repository.UsersRepository;

import com.example.IntegrationAPI.MySql.entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}