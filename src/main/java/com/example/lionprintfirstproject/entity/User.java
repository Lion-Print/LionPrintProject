package com.example.lionprintfirstproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String middleName;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String password;
    @Column(nullable = false)
    private String imageUrl;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserDetails asDetailedUser() {
        var authority = new SimpleGrantedAuthority(role.name());
        return new org.springframework.security.core.userdetails.User(phoneNumber, password, true, true, true, true, List.of(authority));
    }

    public static User of(String firstName, String lastName, String middleName, String phoneNumber, String password, String imageUrl, UserRole role) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .phoneNumber(phoneNumber)
                .password(password)
                .imageUrl(imageUrl)
                .role(role)
                .build();
    }

}
