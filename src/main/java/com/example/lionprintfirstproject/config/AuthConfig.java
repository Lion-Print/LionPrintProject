package com.example.lionprintfirstproject.config;

import com.example.lionprintfirstproject.entity.User;
import com.example.lionprintfirstproject.exception.UserNotFoundByPhoneNumberException;
import com.example.lionprintfirstproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthConfig {

    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository.findByPhoneNumber(username).map(User::asDetailedUser)
                .orElseThrow(() -> new UserNotFoundByPhoneNumberException(username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
