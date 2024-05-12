package com.api.Bry.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Bry.models.User;
import com.api.Bry.repositories.UserRepository;

@Service
public class UserService {
    
    // Autowired, "construtor" da interface

    @Autowired
    private UserRepository  userRepository;


    public Optional<User> findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional;
        } else {
            throw new RuntimeException("Could not find the user with ID: " + id);
        }
    }



}
