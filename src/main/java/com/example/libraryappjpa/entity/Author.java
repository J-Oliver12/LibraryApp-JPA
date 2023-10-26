package com.example.libraryappjpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", updatable = false, nullable = false)
    private Long authorId;


    private String firstName;

    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        return authorId != null && authorId.equals(((Author) o).authorId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
