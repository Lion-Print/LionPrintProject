package com.example.lionprintfirstproject.dto;

import com.example.lionprintfirstproject.entity.UserRole;

public record CreateUser(
        String firstName,
        String lastName,
        String middleName,
        String phoneNumber,
        String password,
        String role
) {
}
