package com.example.lionprintfirstproject.exception;

public class PictureNotFoundException extends RuntimeException {
    public PictureNotFoundException() {
        super("error.not_found.picture");
    }
}
