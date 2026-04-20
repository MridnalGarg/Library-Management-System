package com.mridnal.entity;

import java.util.List;
import java.util.UUID;

public class Patron {

    private UUID patronId;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

}
