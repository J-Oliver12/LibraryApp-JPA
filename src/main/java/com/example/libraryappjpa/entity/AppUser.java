package com.example.libraryappjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id", updatable = false, nullable = false)
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(nullable = false)
    private LocalDate regdate;

    @OneToOne
    @JoinColumn(name = "details_id", unique = true)
    private Details details;
}
