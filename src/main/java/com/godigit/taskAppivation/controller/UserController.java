package com.godigit.taskAppivation.controller;

import com.godigit.taskAppivation.dto.LoginDto;
import com.godigit.taskAppivation.dto.UserDto;
import com.godigit.taskAppivation.exception.ResourceAlreadyExistException;
import com.godigit.taskAppivation.model.UserModel;
import com.godigit.taskAppivation.service.UserService;
import lombok.extern.java.Log;
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
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<?>> getUserTask(@RequestHeader() String token) {
        return ResponseEntity.ok(userService.getUserTasks(token));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestHeader() String token) {
        return ResponseEntity.ok(userService.deleteUserByToken(token));
    }

    @PutMapping
    public ResponseEntity<?> updateUserById(@RequestHeader() String token, @RequestBody UserDto user) {
        UserDto userDto = userService.updateUserDetailsByToken(token, user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
