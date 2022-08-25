package com.uhk.application.school.controller;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.*;

import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainControllerImplTest {

    @Autowired
    private LanguageSchool languageSchool;


    @Mock
    private UserRepository userRepository;

    private User userMock = null;

    @BeforeEach
    public void init() {
        userMock = new User();
        userMock.setIdUser(1);
        userMock.setRole("admin");
        userMock.setUsername("admin");
        userMock.setEmail("q@q.cz");
        userMock.setHashedPassword("$2a$10$WceZ.eP/EXu9yRKDXjNscutDA39U30BtJ.Rre4nDrKT8u6MfaICHS");
        userMock.setIcon("");
        userMock.setName("Martin");
        userMock.setSurname("Kvapil");
    }

    @Test
    public void testControllerAvailability() throws Exception {
        assertThat(languageSchool).isNotNull();
    }

    @Test
    public void testUserRepositoryAvailability() throws Exception {
        assertThat(userRepository).isNotNull();
    }


    @Test
    public void saveUserEmptyEmailUser() {
        User user = new User();
        user.setEmail("");

        UserException exception = assertThrows(UserException.class, () -> {
            languageSchool.saveUser(user);
        });
        assertEquals((new UserException("Email uživatele nemůže být prázdný.")).getMessage(), exception.getMessage());
    }

    @Test
    public void saveUserTest() throws Exception {
        when(userRepository.save(userMock)).thenReturn(userMock);
        User user = languageSchool.saveUser(userMock);
        assertEquals(userMock.getEmail(), user.getEmail());
    }
}