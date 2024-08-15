package com.example.javaguides.controller;

import com.example.javaguides.dto.UserDto;
import com.example.javaguides.entitiy.User;
import com.example.javaguides.exception.ErrorDetails;
import com.example.javaguides.exception.ResourceNotFoundException;
import com.example.javaguides.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("apis/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto savedUser=userService.createUser(userDto);
        return new  ResponseEntity<>(savedUser, HttpStatus.CREATED);


    }


    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){

        UserDto userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUser = userService.getAllUser();
        return  new ResponseEntity<>(allUser,HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long Id ,@Valid @RequestBody UserDto user){
        user.setId(Id);
        UserDto user1 = userService.UpdateUser(user);
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteByID(@PathVariable("id") Long userId){
        userService.DeleteUser(userId);
        return new ResponseEntity<>("user successfully deleted",HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResoucrNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
//        ErrorDetails errorDetails= new ErrorDetails(
//                LocalDate.now(),
//                resourceNotFoundException.getMessage(),
//                webRequest.getDescription(false),
//                "User not found"
//        );
//        return new  ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }

}
