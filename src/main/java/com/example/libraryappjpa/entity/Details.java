package com.example.libraryappjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id", updatable = false, nullable = false)
    private Long detailsId;

    @Column(unique = true)
    private String email;

    private String name;
    private LocalDate birthDate;

    @OneToOne(mappedBy = "details", optional = false)
    private AppUser appUser;

}
