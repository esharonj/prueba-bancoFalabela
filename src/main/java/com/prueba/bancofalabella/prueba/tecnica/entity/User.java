package com.prueba.bancofalabella.prueba.tecnica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_users_email", columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {



    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false, unique = true)
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    )
    private String email;


    @Column(nullable = false)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=(?:.*\\d){2,}).{6,}$"
    )
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Phone> phones = new ArrayList<>();


    @Column(nullable = false)
    private LocalDateTime created;


    @Column(nullable = false)
    private LocalDateTime modified;


    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogin;


    @Column(nullable = false, length = 512)
    private String token;


    @Column(name = "is_active", nullable = false)
    private boolean isActive;


    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.modified = now;
        this.lastLogin = now;
        this.isActive = true;
    }


    @PreUpdate
    public void preUpdate() {
        this.modified = LocalDateTime.now();
    }
}
