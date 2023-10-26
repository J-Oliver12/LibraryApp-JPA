package com.example.libraryappjpa.dao;

import com.example.libraryappjpa.entity.Book;

import java.util.Collection;

public interface BookDao {

    Book findById(int bookId);

    Collection<Book> findAll();

    Book create(Book book);

    Book update(Book book);

    void delete(int bookId);
}
