package com.example.javaguides.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private long id;
    @NotEmpty(message = "first name should not be empty or null")
    private String lastName;
    @NotEmpty(message = "last name should not be empty or null")
    private String firstName;
    @NotEmpty(message = "email name should not be empty or null")
    @Email(message = "should be a valid email")
    private String email;
}
