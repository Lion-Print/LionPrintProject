package com.example.lionprintfirstproject.controller;

import com.example.lionprintfirstproject.controller.vm.UserVm;
import com.example.lionprintfirstproject.dto.CreateUser;
import com.example.lionprintfirstproject.dto.LoginDto;
import com.example.lionprintfirstproject.dto.ResponseData;
import com.example.lionprintfirstproject.dto.TokenResult;
import com.example.lionprintfirstproject.entity.User;
import com.example.lionprintfirstproject.mapper.UserMapper;
import com.example.lionprintfirstproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    private final UserMapper mapper;

    @PostMapping
    public ResponseData<UserVm> create(
            @RequestParam(value = "picture") MultipartFile photo,
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "middleName") String middleName,
            @RequestParam(value = "phoneNumber") String phoneNumber,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "role") String role
    ) {
        User user = service.create(new CreateUser(firstName, lastName, middleName, phoneNumber, password, role), photo);
        return ResponseData.of(mapper.asUserVm(user));
    }

    @PostMapping("/login")
    public ResponseData<TokenResult> login(@RequestBody LoginDto dto) {
        TokenResult tokenResult = service.login(dto);
        return ResponseData.of(tokenResult);
    }

    @GetMapping
    public ResponseData<List<UserVm>> getAll(Pageable pageable) {
        List<User> users = service.getAll(pageable);
        return ResponseData.of(mapper.asUserList(users));
    }

}
