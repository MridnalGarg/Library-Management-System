package com.mridnal.service;

import com.mridnal.entity.Book;
import com.mridnal.entity.Patron;

import java.util.List;

import static com.mridnal.service.InMemoryStore.patrons;

public interface PatronService {

    public void createPatron(String name, String email);

    public void updatePatron(String patronId, String newName, String newEmail);

    public void removePatron(String patronId);

    public default Patron getPatron(String patronId) {
        return patrons.get(patronId);
    }

}
