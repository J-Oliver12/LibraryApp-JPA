package com.example.libraryappjpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id",updatable = false, nullable = false)
    private Long bookId;

    @Column(unique = true)
    private String isbn;

    private String title;

    private int maxLoanDays;

    @Getter
    private boolean available;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return bookId != null && bookId.equals(((Book) o).bookId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )

    private Set<Author> authors = new HashSet<>();

}






