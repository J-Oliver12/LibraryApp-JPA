package com.example.libraryappjpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
public class BookLoan {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id", updatable = false, nullable = false)
    private Long loanId;

    @Getter
    private LocalDate loanDate;

    @Getter
    private LocalDate dueDate;

    @Getter
    private boolean returned;

    @Getter
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private AppUser borrower;

    @Getter
    @ManyToOne
    @JoinColumn(name = "book_Id")
    private Book book;

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}