package com.godigit.taskAppivation.service;

import com.godigit.taskAppivation.dto.TaskDto;
import com.godigit.taskAppivation.model.TaskModel;
import com.godigit.taskAppivation.model.UserModel;
import com.godigit.taskAppivation.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    public void createTask(long userId, TaskDto task) {
        // Code to create a new task for a user
        UserModel userById = userService.getUserById(userId);
        List<TaskModel> tasks = userById.getTasks();
        tasks.add(convertDtoToEntity(task));
        userService.saveUser(userById);
    }

    public void deleteTask(long taskId) {
        // Code to delete a task
        taskRepository.deleteById(taskId);
    }

    public List<TaskDto> getAllTasksForUserId(Long userId) {
        // Code to retrieve all tasks for a user
        return taskRepository
                .findByUserId(userId)
                .stream().map(this::convertEntityToDto).toList();
    }

    public TaskDto getTaskById(Long taskId) {
        // Code to retrieve a specific task by ID
        TaskModel taskModel = taskRepository.findById(taskId).orElseThrow();
        return convertEntityToDto(taskModel);
    }

    private TaskDto convertEntityToDto(TaskModel taskModel) {
        return modelMapper.map(taskModel, TaskDto.class);
    }

    private TaskModel convertDtoToEntity(TaskDto task) {
        return modelMapper.map(task, TaskModel.class);
    }
}
