package com.example.lionprintfirstproject.controller.vm;

public record UserVm(
        Long id,
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String imageUrl
) {
}
