package com.uhk.application.school.controller;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.*;

import com.uhk.application.school.exception.CourseException;
import com.uhk.application.school.exception.QuestionException;
import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.*;
import com.uhk.application.school.model.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MainControllerImplTest {
    @Autowired
    private LanguageSchool languageSchool;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private TestToQuestion testToQuestionRepository;

    @Mock
    private CourseToTestRepository courseToTestRepository;

    @Mock
    private QuestionRepository questionRepository;

    private User userMock = null;

    private Course courseMock = null;

    private Question questionMock = null;

    @BeforeEach
    public void init() {
        initUser();
        initCourse();
        initQuestion();
    }

    private void initUser() {
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

    private void initCourse() {
        courseMock = new Course();
        courseMock.setIdCourse(1);
        courseMock.setPoints(10);
        courseMock.setIdUser(userMock.getIdUser());
        courseMock.setIdTeachingLanguage(1);
    }

    private void initQuestion() {
        questionMock = new Question();
        questionMock.setIdQuestion(1);
        questionMock.setQuestion("");
    }

    @Test
    public void saveQuestionTest() {
        QuestionException exception = assertThrows(QuestionException.class, () -> {
            languageSchool.saveQuestion(questionMock);
        });
        assertEquals((new QuestionException("Otázku nelze uložit prázdnou.")).getMessage(), exception.getMessage());
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
    public void saveQuestionEmptyEmailUser() {
        User user = new User();
        user.setEmail("");

        UserException exception = assertThrows(UserException.class, () -> {
            languageSchool.saveUser(user);
        });
        assertEquals((new UserException("Email uživatele nemůže být prázdný.")).getMessage(), exception.getMessage());
    }

    @Test
    public void saveCourseEmailUser() {
        Course course = new Course();
        course.setIdTeachingLanguage(0);

        CourseException exception = assertThrows(CourseException.class, () -> {
            languageSchool.saveCourse(course);
        });
        assertEquals((new CourseException("Nebyl zvolen vyučovací jazyk kurzu.")).getMessage(), exception.getMessage());
    }

    @Test
    public void saveUserTest() throws Exception {
        when(userRepository.save(userMock)).thenReturn(userMock);
        User user = languageSchool.saveUser(userMock);
        assertEquals(userMock.getEmail(), user.getEmail());
    }

    @Test
    public void testCourseRepositoryAvailability() throws Exception {
        assertThat(courseRepository).isNotNull();
    }

    @Test
    public void testToQuestionRepositoryAvailability() throws Exception {
        assertThat(testToQuestionRepository).isNotNull();
    }

    @Test
    public void courseToTestAvailability() throws Exception {
        assertThat(courseToTestRepository).isNotNull();
    }
}