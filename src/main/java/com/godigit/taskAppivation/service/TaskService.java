package com.godigit.taskAppivation.service;

import com.godigit.taskAppivation.dto.CategoryDto;
import com.godigit.taskAppivation.dto.TaskDto;
import com.godigit.taskAppivation.model.CategoryModal;
import com.godigit.taskAppivation.model.TaskModel;
import com.godigit.taskAppivation.model.UserModel;
import com.godigit.taskAppivation.repository.TaskRepository;
import com.godigit.taskAppivation.util.TokenUtility;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenUtility tokenUtility;

    @Transactional
    public void createTask(String token, TaskDto task) {
        // Code to create a new task for a user
        Long userId = tokenUtility.decodeAsLong(token);
        UserModel userById = userService.getUserById(userId);

        List<TaskModel> tasks = userById.getTasks();

        TaskModel taskModel = convertDtoToEntity(task);
        CategoryModal receiveCategory = taskModel.getCategory();

        CategoryModal category = categoriesService.getByName(receiveCategory.getName());

        if(category != null) {
            taskModel.setCategory(category);
        } else {
            taskModel.setCategory(receiveCategory);
        }

        tasks.add(taskModel);

        userService.saveUser(userById);
    }

    public void deleteTask(long taskId) {
        // Code to delete a task
        taskRepository.deleteById(taskId);
    }

    public List<?> getAllTaskByToken(String token) {
        Long userId = tokenUtility.decodeAsLong(token);
        return getAllTasksForUserId(userId);
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
