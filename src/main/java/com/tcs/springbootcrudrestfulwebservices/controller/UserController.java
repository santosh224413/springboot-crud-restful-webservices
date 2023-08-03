package com.tcs.springbootcrudrestfulwebservices.controller;

import com.tcs.springbootcrudrestfulwebservices.entity.User;
import com.tcs.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;
import com.tcs.springbootcrudrestfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //get all users
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " +userId));

    }
    //create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
    //update user
    @PutMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long userId){
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);

    }
    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userID){
        User existingUser = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
