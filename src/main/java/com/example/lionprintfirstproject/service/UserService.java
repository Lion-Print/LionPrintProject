package com.example.lionprintfirstproject.service;

import com.example.lionprintfirstproject.dto.CreateUser;
import com.example.lionprintfirstproject.dto.LoginDto;
import com.example.lionprintfirstproject.dto.TokenResult;
import com.example.lionprintfirstproject.entity.User;
import com.example.lionprintfirstproject.entity.UserRole;
import com.example.lionprintfirstproject.exception.*;
import com.example.lionprintfirstproject.repository.UserRepository;
import com.example.lionprintfirstproject.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public User create(CreateUser dto, MultipartFile file) {

        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new UserExistByPhoneNumberException(dto.phoneNumber());
        }

        String imageUrl = saveUserPicture(file);

        User user = User.of(dto.firstName(), dto.lastName(), dto.middleName(), dto.phoneNumber(), passwordEncoder.encode(dto.password()), imageUrl, UserRole.valueOf(dto.role()));

        return repository.save(user);
    }

    private static final String BASE_IMAGE_PATH = "src/main/resources/";

    private String saveUserPicture(MultipartFile picture) throws IOException {
        if (Objects.isNull(picture) || picture.isEmpty()) {
            throw new PictureNotFoundException();
        }
        String imageUrl = String.format("images/%s.jpg", UUID.randomUUID());
        File file = new File(BASE_IMAGE_PATH + imageUrl);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] mainContent = picture.getBytes();
            outputStream.write(mainContent);
        }
        return imageUrl;
    }

    @Value("${jwt.secret}")
    private String tokenSecret;

    public TokenResult login(LoginDto dto) {
        User user = repository.findByPhoneNumber(dto.phoneNumber()).orElseThrow(() -> new UserNotFoundByPhoneNumberException(dto.phoneNumber()));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserPasswordNoMatchesException();
        }

        String token = JwtUtils.generateToken(user.asDetailedUser(), tokenSecret);
        return new TokenResult(token);
    }

    public List<User> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public User getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
    }
}
