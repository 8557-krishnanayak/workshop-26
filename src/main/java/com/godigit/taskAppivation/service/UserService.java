package com.godigit.taskAppivation.service;

import com.godigit.taskAppivation.dto.LoginDto;
import com.godigit.taskAppivation.dto.TaskDto;
import com.godigit.taskAppivation.dto.UserDto;
import com.godigit.taskAppivation.exception.PasswordMismatchException;
import com.godigit.taskAppivation.exception.ResourceAlreadyExistException;
import com.godigit.taskAppivation.exception.ResourceNotFoundException;
import com.godigit.taskAppivation.model.TaskModel;
import com.godigit.taskAppivation.model.UserModel;
import com.godigit.taskAppivation.repository.UserRepository;
import com.godigit.taskAppivation.util.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;

    private UserDto convertToDto(UserModel userModel) {
        return modelMapper.map(userModel, UserDto.class);
    }

    private UserModel convertToEntity(UserDto user) {
        return modelMapper.map(user, UserModel.class);
    }

    public UserDto registerUser(UserDto userDto) {
        UserModel userModel = convertToEntity(userDto);
        return registerUser(userModel);
    }

    public UserDto registerUser(UserModel userData) {
        UserModel userByUsername = userRepository.findByUsername(userData.getUsername());
        UserModel userByEmail = userRepository.findByEmail(userData.getEmail());

        if ((userByUsername != null) || (userByEmail != null)) {
            throw new ResourceAlreadyExistException("User Already Exists with that credential");
        }
        UserModel save_user = userRepository.save(userData);
        UserDto userDto = convertToDto(save_user);
        userDto.setPassword(null);
        return userDto;
    }

    public String login(LoginDto loginDto) {
        UserModel user = userRepository.findByUsername(loginDto.getUsername());

        if (!user.getPassword().equals(loginDto.getPassword()))
            throw new PasswordMismatchException("Password does not match");

        return tokenUtility.getToken(user.getId());
    }

    public void saveUser(UserModel userModel) {
        convertToDto(userRepository.save(userModel));
    }

    //    update by token is implementing this method
    public UserDto updateUserDetails(long userId, UserModel user) {
        UserModel userById = getUserById(userId);

        if (user.getUsername() != null)
            userById.setUsername(user.getUsername());
        if (user.getPassword() != null)
            userById.setPassword(user.getPassword());

        return convertToDto(userRepository.save(userById));
    }

    //    delete by token is implementing this method
    public String deleteUser(Long userId) {
        // Code to delete a user
        userRepository.deleteById(userId);
        return "user with the id " + userId + " has been Deleted";
    }

    //    implement by the getUserByToken
    public UserModel getUserById(long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public List<TaskDto> getUserTasks(String token) {
        Long userId = tokenUtility.decodeAsLong(token);
        UserModel user = userRepository.findById(userId).orElseThrow();
        return user.getTasks().stream().map(this::convertToDto).toList();
    }

    private TaskDto convertToDto(TaskModel taskModel) {
        return modelMapper.map(taskModel, TaskDto.class);
    }

    private TaskModel convertToEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, TaskModel.class);
    }

    //    Token Method are below
    public UserDto updateUserDetailsByToken(String token, UserDto user) {
        Long user_id = tokenUtility.decodeAsLong(token);
        UserModel userModel = convertToEntity(user);
        return updateUserDetails(user_id, userModel);
    }

    public String deleteUserByToken(String token) {
        Long user_id = tokenUtility.decodeAsLong(token);
        return deleteUser(user_id);
    }

    public UserDto getUserByToken(String token) {
        Long user_id = tokenUtility.decodeAsLong(token);
        UserModel userById = getUserById(user_id);
        return convertToDto(userById);
    }
}
