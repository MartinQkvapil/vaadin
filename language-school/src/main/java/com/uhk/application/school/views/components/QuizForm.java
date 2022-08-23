package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.CourseToTest;
import com.uhk.application.school.model.entity.Question;
import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.security.AuthenticationService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * A Designer generated component for the quiz-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("quiz-form")
@JsModule("./views/quiz-form.ts")
public class QuizForm extends LitTemplate {
    @Autowired
    private LanguageSchool school;
    @Autowired
    private AuthenticationService authentication;

    @Id("buttonOption1")
    private Button buttonOption1;
    @Id("buttonOption2")
    private Button buttonOption2;
    @Id("buttonOption3")
    private Button buttonOption3;
    @Id("buttonOption4")
    private Button buttonOption4;
    @Id("gridUserTests")
    private Grid<Test> gridUserTests;
    @Id("inputQuestion")
    private TextField inputQuestion;
    @Id("progressBar")
    private ProgressBar progressBar;
    @Id("buttonPrevious")
    private Button buttonPrevious;
    @Id("buttonNext")
    private Button buttonNext;

    private List<Question> questions;
    private int position = 0;
    private Test selectedTest = null;
    private CourseToTest selectedCourseToTest = null;

    private int points;
    private int badPoints;

    @Id("labelCorrect")
    private Label labelCorrect;
    @Id("labelWrong")
    private Label labelWrong;

    @PostConstruct
    public void init() {
        gridUserTests.addColumn(Test::getTestName).setHeader("Name").setSortable(true);
        gridUserTests.addColumn(Test::getQuestionCount).setHeader("Počet otázek").setSortable(true);
        gridUserTests.addColumn(Test::getTestLanguage).setHeader("Jazyk").setSortable(true);
        List<Test> tests = school.getAllTestsByUserId(authentication.get().get().getIdUser());
        gridUserTests.setItems(tests);

        gridUserTests.addItemClickListener(runQuizListener());

        buttonOption1.addClickListener(answerAction());
        buttonOption2.addClickListener(answerAction());
        buttonOption3.addClickListener(answerAction());
        buttonOption4.addClickListener(answerAction());

        buttonPrevious.addClickListener(moveToPreviousQuestion());
        buttonNext.addClickListener(moveToNextQuestion());
        progressBar.setValue(0.0);

        buttonOption1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonOption2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonOption3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonOption4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    private ComponentEventListener<ClickEvent<Button>> moveToNextQuestion() {
        return event -> {
            if (position < questions.size()-1) {
                position++;
                questionVoid();
            }
        };
    }

    private ComponentEventListener<ClickEvent<Button>> moveToPreviousQuestion() {
        return event -> {
            if (position > 0) {
                position--;
                questionVoid();
            }
        };
    }

    private ComponentEventListener<ItemClickEvent<Test>> runQuizListener() {
        return event -> {
            selectedTest = event.getItem();
            selectedCourseToTest = school.getCourseToTestByUserAndTestId(authentication.get().get().getIdUser(), selectedTest.getIdTest());

            inputQuestion.setValue("");
            labelCorrect.setText("0");
            labelWrong.setText("0");
            position = 0;
            badPoints = 0;
            points = 0;
            questions = school.getAllQuestionsByTestId(selectedTest.getIdTest());
            questionVoid();
        };
    }

    private void questionVoid() {
        double progressBarPosition = (double) position/questions.size();
        progressBar.setValue(progressBarPosition + 0.1);

        List<String> answers = new ArrayList<>();
        answers.add(questions.get(position).getAnswer1());
        answers.add(questions.get(position).getAnswer2());
        answers.add(questions.get(position).getAnswer3());
        answers.add(questions.get(position).getAnswer4());
        Collections.shuffle(answers);

        inputQuestion.setValue(questions.get(position).getQuestion());

        buttonOption1.setText(answers.get(0));
        buttonOption2.setText(answers.get(1));
        buttonOption3.setText(answers.get(2));
        buttonOption4.setText(answers.get(3));

        inputQuestion.setLabel("Otázka: " + (position+1) + " / " + questions.size());
    }
    private ComponentEventListener<ClickEvent<Button>> answerAction() {
        return event -> {
            Button button = event.getSource();
            if (selectedCourseToTest == null  || selectedCourseToTest.getDone() <= 0) {
                if (Objects.equals(questions.get(position).getAnswer1(), button.getText())) {
                    Notification notification = Notification.show("Správná odpověď!");
                    notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    points = points + questions.get(position).getPoints();
                    labelCorrect.setText(String.valueOf(points));
                    position++;
                } else {
                    badPoints++;
                    labelWrong.setText(String.valueOf(badPoints));
                    Notification notification = Notification.show("Špatná odpověď zkus to znovu!");
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
                if (position >= questions.size()) {
                    Notification notification = Notification.show("Konec testu!");
                    notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
                    progressBar.setValue(1);
                    position = questions.size();

                    selectedCourseToTest.setDone(selectedCourseToTest.getDone() + 1);
                    selectedCourseToTest.setWrongAnswers(badPoints);
                    selectedCourseToTest.setCorrectAnswers(points);

                    // TODO update points count in course!!!
                    try {
                        school.saveCourseToTest(selectedCourseToTest);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return;
                }
                questionVoid();
            } else {
                Notification notification = Notification.show("Tento test jsme již vyplnili!");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        };
    }

    /**
     * Creates a new QuizForm.
     */
    public QuizForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
