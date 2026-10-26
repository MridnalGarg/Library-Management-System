package com.mridnal.service;

import com.mridnal.entity.Book;

import java.util.List;

public interface SearchBookService {

    List<Book> findBooksByTitle(String title);
    Book findBookByIsbn(String isbn);
    List<Book> findAllBooksAvailable();

}
