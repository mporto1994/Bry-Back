package com.api.Bry.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Bry.models.User;
import com.api.Bry.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    // Autowired, "construtor" da interface
    @Autowired
    private UserRepository  userRepository;


    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find the user ID:" + id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Pesquisar mais
    // @Transactional
    public User create(User userObj) {
        userObj.setId(null);
        userObj =  this.userRepository.save(userObj);

        return userObj;
    }

    public User update(User user){
        User existingUser = findById(user.getId());
    
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setPicture(user.getPicture());
    
        return this.userRepository.save(existingUser);
    }


    public void delete(Long id){
        
        findById(id);

        try {
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("User not found id:" + id);
        }
    }

}
