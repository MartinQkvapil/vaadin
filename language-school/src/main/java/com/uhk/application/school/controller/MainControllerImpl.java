package com.uhk.application.school.controller;

import com.uhk.application.school.model.entity.*;
import com.uhk.application.school.model.repository.*;
import com.uhk.application.school.model.validator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Controller
@Transactional
public class MainControllerImpl implements LanguageSchool{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseValidator courseValidator;
    @Autowired
    private TeachingLanguagesRepository teachingLanguagesRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestValidator testValidator;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionValidator questionValidator;

    @Autowired
    private CourseToTestRepository courseToTestRepository;

    @Autowired
    private CourseToTestValidator courseToTestValidator;
    @Autowired
    private TestToQuestionRepository testToQuestionRepository;

    @Override
    public User getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<TeachingLanguages> getAllLanguages() {
        return teachingLanguagesRepository.findAll();
    }

    @Override
    public User saveUser(User user) throws Exception {
        User userSaved = null;
        if (user != null) {
            userValidator.validate(user);
            userSaved = userRepository.findByEmail(user.getEmail());
            if (userSaved != null) {
                user.setIdUser(userSaved.getIdUser());
            }
        }
        return userRepository.save(user);
    }

    @Override
    public Course saveCourse(Course course) throws Exception {
        Course savedCourse = null;
        if (course != null) {
            courseValidator.validate(course);
            savedCourse = courseRepository.findByUserIdAndTeachingLanguage(course.getIdUser(), course.getIdTeachingLanguage());
            if (savedCourse != null) {
                course.setIdCourse(savedCourse.getIdCourse());
            }
        }
        return courseRepository.save(course);
    }

    @Override
    public List<Test> getAllTestsByUserId(int userId) {
        return testRepository.findAllByUserId(userId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void saveQuestion(Question question) throws Exception {
        questionValidator.validate(question);

        Optional<Question> temp = questionRepository.findById(question.getIdQuestion());
        if (temp.isPresent()) {
            question.setIdQuestion(temp.get().getIdQuestion());
        }
        
        questionRepository.save(question);
    }
    @Override
    public void removeQuestion(Question question) {
        questionRepository.delete(question);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public List<Question> getAllQuestionsByTestId(int idTest) {
        return questionRepository.findAllByTestId(idTest);
    }

    @Override
    public Test saveTest(Test test) throws Exception {
        testValidator.validate(test);
        return testRepository.save(test);
    }

    @Override
    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public void removeTest(Test test) {
         courseToTestRepository.deleteAllById(courseToTestRepository.getByTestId(test.getIdTest()));
         testToQuestionRepository.deleteAllById(testToQuestionRepository.getByTestId(test.getIdTest()));
         testRepository.delete(test);
    }

    public void removeTestToQuestion(Question currentQuestion, Test currentTest) {
        TestToQuestion c2t = testToQuestionRepository.getByTestIdAndQuestionId(currentTest.getIdTest(), currentQuestion.getIdQuestion());
        testToQuestionRepository.deleteById(c2t.getIdTestToQuestion());
    }

    public void saveTestToQuestion(TestToQuestion testToQuestion) {
        testToQuestionRepository.save(testToQuestion);
    }

    public List<Test> getAllTestsByLanguage(int idTeachingLanguage) {
        return testRepository.findAllByLanguageId(idTeachingLanguage);
    }

    public CourseToTest getCourseToTestByUserAndTestId(int idUser, int idTest) {
        return courseToTestRepository.findByUserIdAndTestId(idUser, idTest);
    }

    public CourseToTest saveCourseToTest(CourseToTest courseToTest) throws Exception {
        CourseToTest savedCourseToTest = null;
        if (courseToTest != null) {
            courseToTestValidator.validate(courseToTest);
            savedCourseToTest = courseToTestRepository.findByCourseIdAndTestId(courseToTest.getIdCourse(), courseToTest.getIdTest());
            if (savedCourseToTest != null) {
                courseToTest.setIdCourseToTest(savedCourseToTest.getIdCourseToTest());
            }
        }
        return courseToTestRepository.save(courseToTest);
    }

    public Course getCourseByUserAndLanguage(int idUser, int idTeachingLanguage) {
        return courseRepository.findByUserIdAndTeachingLanguage(idUser, idTeachingLanguage);
    }
}
