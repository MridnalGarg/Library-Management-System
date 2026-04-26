package com.mridnal.entity;

import com.mridnal.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Patron {

    private String patronId;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public Patron(){}

    public Patron(String name, String email) {
        this.patronId = IdGenerator.generateShortUUID();
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
