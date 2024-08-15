package com.example.javaguides.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException{
    private String messsage;

    public  EmailAlreadyExistsException(String messsage){
        super(messsage);
    }
}
