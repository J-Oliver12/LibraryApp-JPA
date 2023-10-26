package com.example.libraryappjpa.dao.impl;

import com.example.libraryappjpa.dao.BookDao;
import com.example.libraryappjpa.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book findById(int bookId) {
        return entityManager.find(Book.class, bookId);
    }

    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    @Transactional
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void delete(int bookId) {
        Book book = findById(bookId);
        if (book != null) {
            entityManager.remove(book);
        }
    }
}
