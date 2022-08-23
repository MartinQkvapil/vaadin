package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.Question;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * A Designer generated component for the question-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("question-form")
@JsModule("./views/question-form.ts")
public class QuestionForm extends LitTemplate {
    @Autowired
    private LanguageSchool school;
    @Id("textAreaQuestion")
    private TextArea textAreaQuestion;
    @Id("inputCorrectAnswer")
    private TextField inputCorrectAnswer;
    @Id("inputWrong1")
    private TextField inputWrong1;
    @Id("inputWrong2")
    private TextField inputWrong2;
    @Id("gridQuestions")
    private Grid<Question> gridQuestions;
    @Id("selectPoints")
    private Select<Integer> selectPoints;
    @Id("buttonSave")
    private Button buttonSave;
    @Id("buttonEdit")
    private Button buttonEdit;
    @Id("inputWrong3")
    private TextField inputWrong3;

    private Question currentQuestion = null;
    @Id("buttonRemove")
    private Button buttonRemove;
    @Id("buttonClear")
    private Button buttonClear;

    @PostConstruct
    public void init() {
        List<Integer> values = new ArrayList<>();
        int x = 1;
        values.add(x);
        selectPoints.setItems(values);
        selectPoints.setValue(values.get(0));

        gridQuestions.addColumn(Question::getIdQuestion).setHeader("ID otázky").setSortable(true);
        gridQuestions.addColumn(Question::getQuestion).setHeader("Otázka").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer1).setHeader("Spravna").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer2).setHeader("Odpověď2").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer3).setHeader("Odpověď3").setSortable(true);
        gridQuestions.addColumn(Question::getAnswer4).setHeader("Odpověď4").setSortable(true);
        gridQuestions.addColumn(Question::getPoints).setHeader("Bodů").setSortable(true);
        List<Question> questions = school.getAllQuestions();
        gridQuestions.setItems(questions);

        buttonSave.addClickListener(addNewQuestionListener());
        buttonRemove.addClickListener(removeQuestionListener());
        buttonClear.addClickListener(refreshQuestionForm());
        buttonEdit.addClickListener(editQuestionListener());
        gridQuestions.addItemClickListener(gridQuestionListener());
    }

    private ComponentEventListener<ClickEvent<Button>> editQuestionListener() {
        return event-> {
            if (currentQuestion == null) {
                Dialog dialog = new Dialog();
                dialog.add(new Text("Není zvolena otázka k editaci."));
                dialog.open();
                return;
            }

            textAreaQuestion.setValue(currentQuestion.getQuestion());
            inputCorrectAnswer.setValue(currentQuestion.getAnswer1());
            inputWrong1.setValue(currentQuestion.getAnswer2());
            inputWrong2.setValue(currentQuestion.getAnswer3());
            inputWrong3.setValue(currentQuestion.getAnswer4());
        };
    }

    private ComponentEventListener<ClickEvent<Button>> removeQuestionListener() {
        return event -> {
            if (currentQuestion == null) {
                Dialog dialog = new Dialog();
                dialog.add(new Text("Není zvolena otázka k vymazání."));
                dialog.open();
                return;
            }
            try {
                school.removeQuestion(currentQuestion);
            } catch (DataIntegrityViolationException ex) {
                Dialog dialog = new Dialog();
                dialog.add(new Text("Otázka nemůže být odstraněna, protože je součástí jiného test."));
                dialog.open();
                return;
            }
            Dialog dialog = new Dialog();
            dialog.add(new Text("Otázka byla úspěšně odebrána."));
            dialog.open();
            refreshQuestionGrid();
        };
    }

    private ComponentEventListener<ItemClickEvent<Question>> gridQuestionListener() {
        return event -> {
           currentQuestion = event.getItem();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> addNewQuestionListener() {
        return event -> {
            Question question = new Question();
            if (currentQuestion != null) {
                question.setIdQuestion(currentQuestion.getIdQuestion());
            }

            question.setQuestion(textAreaQuestion.getValue());
            question.setAnswer1(inputCorrectAnswer.getValue());
            question.setAnswer2(inputWrong1.getValue());
            question.setAnswer3(inputWrong2.getValue());
            question.setAnswer4(inputWrong3.getValue());
            question.setPoints(selectPoints.getValue());


            try {
                school.saveQuestion(question);
                Dialog dialog = new Dialog();
                dialog.add(new Text("Otázka byla úspěšně uložena."));
                dialog.open();
                refreshQuestionGrid();
                refreshInputs();

            } catch (Exception e)
            {
                Dialog dialog = new Dialog();
                dialog.add(new Text(e.getMessage()));
                dialog.open();
            }
        };
    }

    private void refreshQuestionGrid() {
        gridQuestions.setItems(school.getAllQuestions());
    }
    private ComponentEventListener<ClickEvent<Button>> refreshQuestionForm() {
        return event -> {
            refreshQuestionGrid();
            refreshInputs();
        };
    }

    private void refreshInputs() {
        currentQuestion = null;
        inputWrong1.setValue("");
        inputCorrectAnswer.setValue("");
        inputWrong2.setValue("");
        inputWrong3.setValue("");
        textAreaQuestion.setValue("");
    }

    /**
     * Creates a new QuestionForm.
     */
    public QuestionForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
