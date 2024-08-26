package com.godigit.taskAppivation.controller;

import com.godigit.taskAppivation.dto.TaskDto;
import com.godigit.taskAppivation.dto.TaskUpdateDto;
import com.godigit.taskAppivation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //    Assignment
    @PostMapping("/taskId/{task_id}")
    public ResponseEntity<?> updateStatus(@RequestHeader String token, @PathVariable Long task_id, @RequestBody TaskUpdateDto taskUpdateDto) {
        List<?> responseEntity = taskService.updateTask(token, task_id, taskUpdateDto);
        return ResponseEntity.ok(responseEntity);
    }

    @PostMapping("/taskId/{task_id}/userId/{userId}")
    public ResponseEntity<?> assignTask(@RequestHeader String token, @PathVariable Long task_id, @PathVariable Long userId) {

        return ResponseEntity.ok(taskService.assignTaskToUserId(token, userId, task_id));
    }
}
