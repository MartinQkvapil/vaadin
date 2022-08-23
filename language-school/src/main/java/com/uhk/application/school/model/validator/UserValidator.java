package com.uhk.application.school.model.validator;

import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserValidator {
    public void validate(User user) throws UserException {
        if (user == null) {
            throw new UserException("Neexistuje uživatel");
        }
        if (user.getEmail().isEmpty() || Objects.equals(user.getEmail(), "")) {
            throw new UserException("Email uživatele nemůže být prázdný.");
        }
        if (user.getEmail().length() > 45) {
            throw new UserException("Email je moc dlouhý");
        }
        if (user.getName().isEmpty() || Objects.equals(user.getName(), "")) {
            throw new UserException("Jméno uživatele nemůže být prázdné.");
        }
        if (user.getName().length() > 45) {
            throw new UserException("Jméno je moc dlouhé");
        }
        if (user.getSurname().isEmpty() || Objects.equals(user.getSurname(), "")) {
            throw new UserException("Příjmení uživatele nemůže být prázdné.");
        }
        if (user.getSurname().length() > 45) {
            throw new UserException("Příjmení je moc dlouhé");
        }
        if (user.getUsername().isEmpty() || Objects.equals(user.getUsername(), "")) {
            throw new UserException("Login uživatele nemůže být prázdný.");
        }
        if (user.getUsername().length() > 45) {
            throw new UserException("Uživatelské jméno je moc dlouhé");
        }
        if (user.getHashedPassword().isEmpty() || Objects.equals(user.getHashedPassword(), "")) {
            throw new UserException("Heslo uživatele nemůže být prázdné.");
        }
        if (user.getUsername().length() > 100) {
            throw new UserException("Heslo je moc dlouhé");
        }
    }
}
