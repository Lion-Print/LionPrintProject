package com.example.lionprintfirstproject.config;

import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PictureNotFoundException.class)
    public ResponseData<Object> handlePictureNotFoundException(PictureNotFoundException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistByPhoneNumberException.class)
    public ResponseData<Object> handleUserExistByPhoneNumberException(UserExistByPhoneNumberException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByIdException.class)
    public ResponseData<Object> handleUserNotFoundByIdException(UserNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByPhoneNumberException.class)
    public ResponseData<Object> handleUserNotFoundByPhoneNumberException(UserNotFoundByPhoneNumberException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserPasswordNoMatchesException.class)
    public ResponseData<Object> handleUserPasswordNoMatchesException(UserPasswordNoMatchesException e) {
        return ResponseData.errorOf(e.getMessage());
    }

}
