package com.mridnal.service;

import com.mridnal.entity.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookService {

    void addBook(String isbn, String title, String author, String year);
    void removeBook(String isbn);
    void updateBook(Book book);

}
