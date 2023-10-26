package com.example.libraryappjpa.dao;

import com.example.libraryappjpa.entity.Author;

import java.util.Collection;

public interface AuthorDao {

    Author findById(int authorId);

    Collection<Author> findAll();

    Author create(Author author);

    Author update(Author author);

    void delete(int authorId);

    Author findByFirstNameAndLastName(String firstName, String lastName);

}
