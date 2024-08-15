package com.example.javaguides.service.impl;

import com.example.javaguides.dto.UserDto;
import com.example.javaguides.entitiy.User;
import com.example.javaguides.exception.EmailAlreadyExistsException;
import com.example.javaguides.exception.ResourceNotFoundException;
import com.example.javaguides.mapper.UserMapper;
import com.example.javaguides.repository.UserRepository;
import com.example.javaguides.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto user) {
//        User user1 = UserMapper.mapToUserEntity(user);

        Optional<User> email = userRepository.findByEmail(user.getEmail());
        if(email.isPresent()){
            throw  new EmailAlreadyExistsException("email already exists");
        }
        User user1 = modelMapper.map(user,User.class);
        userRepository.save(user1);
//        UserDto userDto = UserMapper.mapToUserDto(user1);
        UserDto userDto = modelMapper.map(user1,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User","id",id.toString())
        );
//        User user = byId.get();
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = userRepository.findAll();
       return all.stream().map((user)-> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto UpdateUser(UserDto user) {
        User user1 = userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("USer","id",String.valueOf(user.getId()))
        );
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        User save = userRepository.save(user1);
        return modelMapper.map(save,UserDto.class);
    }

    @Override
    public void DeleteUser(Long id) {
        User user1 = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("USer","id",id.toString())
        );
        userRepository.deleteById(id);
    }
}
