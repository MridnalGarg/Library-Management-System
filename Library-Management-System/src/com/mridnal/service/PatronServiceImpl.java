package com.mridnal.service;

import com.mridnal.entity.Patron;
import com.mridnal.exception.InvalidInputException;
import com.mridnal.exception.PatronNotFoundException;
import com.mridnal.util.InputValidator;

import static com.mridnal.service.InMemoryStore.logger;
import static com.mridnal.service.InMemoryStore.patrons;

public class PatronServiceImpl implements PatronService {

    private void validatePatronFields(String name, String email){
        if(name == null || email == null){
            throw new InvalidInputException("Name and Email can not be null.");
        }
        if(!InputValidator.isValidName(name)){
            throw new InvalidInputException("Enter a valid name..");
        }
        if(!InputValidator.isValidEmail(email)){
            throw new InvalidInputException("Enter a valid email..");
        }
    }

    @Override
    public void createPatron(String name, String email) {
        try {
            validatePatronFields(name, email);
            Patron patron = new Patron(name, email);
            patrons.put(patron.getPatronId(), patron);
            logger.info("Patron created successfully with ID: " + patron.getPatronId());
        } catch (InvalidInputException e) {
            logger.warning(">>> Validation Error: " + e.getMessage());
        }
    }

    @Override
    public void updatePatron(String patronId, String newName, String newEmail) {
        try{
            if (!patrons.containsKey(patronId)) {
                throw new PatronNotFoundException("Patron with ID " + patronId + " not found.");
            }

            validatePatronFields(newName, newEmail);

            Patron patron = patrons.get(patronId);
            patron.setName(newName);
            patron.setEmail(newEmail);

            logger.info("Patron ID " + patronId + " updated successfully.");
        } catch (Exception e) {
            logger.info(">>> Error: " + e.getMessage());
        }
    }

    @Override
    public void removePatron(String patronId) {
        try {
            if (!patrons.containsKey(patronId)) {
                throw new PatronNotFoundException("Cannot remove: Patron ID " + patronId + " does not exist.");
            }

            patrons.remove(patronId);
            logger.info("Patron ID " + patronId + " removed from system.");
        } catch (Exception e) {
            logger.info(">>> Error: " + e.getMessage());
        }
    }
}
