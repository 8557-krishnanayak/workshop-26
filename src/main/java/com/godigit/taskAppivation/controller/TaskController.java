package com.godigit.taskAppivation.controller;

import com.godigit.taskAppivation.dto.TaskDto;
import com.godigit.taskAppivation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<?> saveTask(@RequestBody TaskDto taskDto, @RequestHeader String token) {
        taskService.createTask(token, taskDto);
        return new ResponseEntity<>("Task is Created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task with Task id " + id + " has been delete");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllTaskById(@RequestHeader String token) {
        return ResponseEntity.ok(taskService.getAllTaskByToken(token));
    }
}
