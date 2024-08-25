package com.godigit.taskAppivation.controller;

import com.godigit.taskAppivation.exception.ResourceAlreadyExistException;
import com.godigit.taskAppivation.model.UserModel;
import com.godigit.taskAppivation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.registerUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<?>> getUserTask(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserTasks(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserModel user) {
        userService.updateUserDetails(id, user);
        return new ResponseEntity<>("user id " + id + " has been updated", HttpStatus.OK);
    }

}
