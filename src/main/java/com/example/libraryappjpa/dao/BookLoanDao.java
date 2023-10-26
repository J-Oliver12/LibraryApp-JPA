package com.example.libraryappjpa.dao;

import com.example.libraryappjpa.entity.BookLoan;

import java.util.Collection;

public interface BookLoanDao {

    BookLoan findById(int loanId);

    Collection<BookLoan> findAll();

    BookLoan create(BookLoan bookLoan);

    BookLoan update(BookLoan bookLoan);

    void delete(int loanId);
}
