package com.mridnal.service;

import com.mridnal.entity.Book;
import com.mridnal.entity.Patron;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class InMemoryStore {
    public static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final Map<String, Patron> patrons = new HashMap<>();

    public static final Map<String, Book> inventory = new HashMap<>();

}
