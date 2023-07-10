package com.example.lionprintfirstproject.exception;

public class UserPasswordNoMatchesException extends RuntimeException {
    public UserPasswordNoMatchesException() {
        super("error.no_matches.password");
    }
}
