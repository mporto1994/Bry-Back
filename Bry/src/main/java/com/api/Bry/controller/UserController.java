package com.api.Bry.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.Bry.models.User;
import com.api.Bry.models.User.CreateUser;
import com.api.Bry.models.User.UpdateUser;
import com.api.Bry.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;

    // post/get Mapping é para definição da rota

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = this.userService.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = this.userService.findAll();
    
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/register")
    @Validated(CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User userObj) {
        this.userService.create(userObj);
    
        String uri = "/user/" + userObj.getId(); 
        // Constrói manualmente a URI, que é menos verboso do que aproveitar a uri la de cima
    
        return ResponseEntity.created(URI.create(uri)).build();
    }
    
    @PutMapping("/update/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@Valid  @PathVariable Long id, @RequestBody User userObj ) {
        userObj.setId(id);
        userObj = this.userService.update(userObj);

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> delete( @PathVariable Long id ) {

        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
