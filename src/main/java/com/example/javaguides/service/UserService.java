package com.example.javaguides.service;

import com.example.javaguides.dto.UserDto;
import com.example.javaguides.entitiy.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUser();

    UserDto UpdateUser(UserDto user);

    void DeleteUser(Long id);
}
