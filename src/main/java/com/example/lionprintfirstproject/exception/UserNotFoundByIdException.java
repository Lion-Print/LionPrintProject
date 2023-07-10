package com.example.lionprintfirstproject.exception;

import lombok.Getter;

@Getter
public class UserNotFoundByIdException extends RuntimeException {

    private final Long id;

    public UserNotFoundByIdException(Long id) {
        super("error.not_found.user.by_id");
        this.id = id;
    }
}
