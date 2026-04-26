package com.mridnal.entity;

import com.mridnal.util.IdGenerator;

import java.time.LocalDateTime;

public class LendingRecord {

    private int loanId;
    private Book book;
    private Patron patron;
    private LocalDateTime checkoutDate;
    private LocalDateTime returnDate;

    public LendingRecord(Book book, Patron patron, LocalDateTime checkoutDate, LocalDateTime returnDate) {
        this.loanId = IdGenerator.nextLoadId();
        this.book = book;
        this.patron = patron;
        this.checkoutDate = checkoutDate;
        this.returnDate = returnDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDateTime getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
