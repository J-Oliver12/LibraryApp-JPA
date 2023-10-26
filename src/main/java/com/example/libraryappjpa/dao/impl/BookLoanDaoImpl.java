package com.example.libraryappjpa.dao.impl;


import com.example.libraryappjpa.dao.BookLoanDao;
import com.example.libraryappjpa.entity.BookLoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class BookLoanDaoImpl implements BookLoanDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookLoan findById(int loanId) {
        return entityManager.find(BookLoan.class, loanId);
    }

    @Override
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("SELECT b FROM BookLoan b", BookLoan.class).getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(int loanId) {
        BookLoan bookLoan = findById(loanId);
        if (bookLoan != null) {
            entityManager.remove(bookLoan);
        }
    }
}
