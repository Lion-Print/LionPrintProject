package com.example.lionprintfirstproject.exception;

import lombok.Getter;

@Getter
public class UserNotFoundByPhoneNumberException extends RuntimeException {

    private final String phoneNumber;

    public UserNotFoundByPhoneNumberException(String phoneNumber) {
        super("error.not_found.user.by_phone_number");
        this.phoneNumber = phoneNumber;
    }
}
