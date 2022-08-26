package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.*;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * A Designer generated component for the manage-tests-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("manage-tests-form")
@JsModule("./views/manage-tests-form.ts")
public class ManageTestsForm extends LitTemplate {
    @Autowired
    private LanguageSchool school;

    @Id("gridTests")
    private Grid<Test> gridTests;
    @Id("gridQuestions")
    private Grid<Question> gridQuestions;
    private Test currentTest;
    private Question currentQuestion = null;
    @Id("selectQuestion")
    private Select<Question> selectQuestion;
    @Id("buttonRemoveTest")
    private Button buttonRemoveTest;
    @Id("buttonCreateTest")
    private Button buttonCreateTest;
    @Id("selectLanguage")
    private Select<TeachingLanguages> selectLanguage;
    @Id("inputTestName")
    private TextField inputTestName;
    @Id("buttonRemoveQuestion")
    private Button buttonRemoveQuestion;
    @Id("buttonAddQuestion")
    private Button buttonAddQuestion;

    @PostConstruct
    public void init() {
        selectLanguage.setItemLabelGenerator(TeachingLanguages::getName);
        List<TeachingLanguages> languages = school.getAllLanguages();
        selectLanguage.setItems(languages);
        selectLanguage.setValue(languages.get(0));

        buttonCreateTest.addClickListener(createTestListener());
        buttonRemoveTest.addClickListener(removeTestListener());
        buttonRemoveQuestion.addClickListener(removeQuestionListener());
        buttonAddQuestion.addClickListener(AddQuestionListener());

        gridTests.addItemClickListener(selectTestListener());
        gridQuestions.addItemClickListener(selectQuestionListener());


        selectQuestion.setItemLabelGenerator(Question::getQuestion);
        List<Question> questionsAll = school.getAllQuestions();
        selectQuestion.setItems(questionsAll);
        selectQuestion.setValue(questionsAll.get(0));

        gridTests.addColumn(Test::getTestName).setHeader("Name").setSortable(true);
        gridTests.addColumn(Test::getQuestionCount).setHeader("Počet otázek").setSortable(true);
        gridTests.addColumn(Test::getTestLanguage).setHeader("Jazyk").setSortable(true);
        List<Test> tests = school.getAllTestsByLanguage(selectLanguage.getValue().getIdTeachingLanguage());
        gridTests.setItems(tests);

        gridQuestions.addColumn(Question::getQuestion).setHeader("Otázka").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer1).setHeader("Správná-1").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer2).setHeader("Klamná-2").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer3).setHeader("Klamná-3").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer4).setHeader("Klamná-4").setSortable(true);

        if (!tests.isEmpty() && currentTest != null) {
            gridTests.select(tests.get(0));
            List<Question> questions = school.getAllQuestionsByTestId(currentTest.getIdTest());
            gridQuestions.setItems(questions);
        }

        selectLanguage.addValueChangeListener(event -> {
            refreshTests();
        });
    }

    private ComponentEventListener<ClickEvent<Button>> AddQuestionListener() {
        return event -> {
            if (currentTest != null) {
                TestToQuestion t2q = new TestToQuestion();
                t2q.setIdTest(currentTest.getIdTest());
                t2q.setIdQuestion(selectQuestion.getValue().getIdQuestion());
                try {
                    school.saveTestToQuestion(t2q);
                    Notification notification = Notification.show("Otázka úspěšně přidána k testu!");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    refreshQuestions();
                    refreshQuestionSelect();
                } catch (DataIntegrityViolationException e) {
                    Notification notification = Notification.show("Test již tuto otázku obsahuje!");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }

        };
    }

    private void refreshQuestions() {
        gridQuestions.setItems(school.getAllQuestionsByTestId(currentTest.getIdTest()));
    }

    private ComponentEventListener<ItemClickEvent<Question>> selectQuestionListener() {
        return event -> {
            currentQuestion = event.getItem();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> removeQuestionListener() {
        return event -> {
            if (currentQuestion != null && currentTest != null) {
                try {
                    school.removeTestToQuestion(currentQuestion, currentTest);
                    Notification notification = Notification.show("Otázka byla odbrána z testu!");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    refreshQuestions();
                } catch (Exception e)  {
                    Notification notification = Notification.show("Není možné odebrat otázku z testu!" + e.getMessage());
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> removeTestListener() {
        return event -> {
            if (currentTest != null) {
                try {
                    school.removeTest(currentTest);
                    refreshTests();
                    Notification notification = Notification.show("Test odebrán!");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                } catch (Exception e) {
                    Notification notification = Notification.show("Test není možné odebrat!" + e.getMessage());
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        };
    }

    private ComponentEventListener<ItemClickEvent<Test>> selectTestListener() {
        return event -> {
            currentTest = event.getItem();
            refreshQuestions();
            refreshQuestionSelect();
        };
    }

    private void refreshQuestionSelect() {
        if (currentTest != null) {
            selectQuestion.setItems(school.getAllQuestionsNotInTest(currentTest.getIdTest()));
        }
    }

    private ComponentEventListener<ClickEvent<Button>> createTestListener() {
        return event -> {
            Test test = new Test();
            test.setTestName(inputTestName.getValue());
            test.setQuestionCount(5);
            test.setTestLanguage(selectLanguage.getValue().getIdTeachingLanguage());
            try {
                school.saveTest(test);
                Notification notification = Notification.show("Nový test vytvořen!");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                Test savedTest = school.getTestByName(test.getTestName());
                List<Course> courses = school.getAllCourses(test.getTestLanguage());
                for (Course course: courses) {
                    CourseToTest testToCourse = new CourseToTest();
                    testToCourse.setDone(0);
                    testToCourse.setIdTest(savedTest.getIdTest());
                    testToCourse.setIdCourse(course.getIdCourse());

                    try {
                        school.saveCourseToTest(testToCourse);
                    } catch (Exception e) {
                        Notification error = Notification.show("Chyba při distribuci testů." + e.getMessage());
                        error.addThemeVariants(NotificationVariant.LUMO_ERROR);
                        return;
                    }
                }
                refreshTests();
            } catch (Exception e) {
                Notification notification = Notification.show("Test není možné vytvořit nový test!" + e.getMessage());
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        };
    }

    private void refreshTests() {
        gridTests.setItems(school.getAllTestsByLanguage(selectLanguage.getValue().getIdTeachingLanguage()));
    }

    /**
     * Creates a new ManageTestsForm.
     */
    public ManageTestsForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
