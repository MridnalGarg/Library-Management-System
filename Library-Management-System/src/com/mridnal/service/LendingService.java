package com.mridnal.service;

import com.mridnal.entity.Book;
import com.mridnal.entity.Patron;

public interface LendingService {

    public void checkoutBook(String patronId, String isbn);

    public void returnBook(String patronId, String isbn);

    public void showBorrowHistory(String patronId);

}
