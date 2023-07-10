package com.example.lionprintfirstproject.exception;

import lombok.Getter;

@Getter
public class UserExistByPhoneNumberException extends RuntimeException {

    private final String phoneNumber;

    public UserExistByPhoneNumberException(String phoneNumber) {
        super("error.duplicate.user.by_phone_number");
        this.phoneNumber = phoneNumber;
    }
}
