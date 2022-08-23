package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.exception.CourseException;
import com.uhk.application.school.exception.CourseToTestException;
import com.uhk.application.school.exception.UserException;
import com.uhk.application.school.model.entity.*;
import com.uhk.application.school.model.security.AuthenticationService;
import com.uhk.application.school.model.security.LoginService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * A Designer generated component for the home-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("home-form")
@JsModule("./views/home-form.ts")
public class HomeForm extends LitTemplate {

    @Autowired
    private LanguageSchool school;

    @Autowired
    private AuthenticationService authentication;

    @Id("languageGrid")
    private Grid<TeachingLanguages> languageGrid;

    @Id("orderCourseButton")
    private Button orderCourseButton;

    @Id("selectLanguages")
    private Select<TeachingLanguages> selectLanguages;
    @Id("inputSurname")
    private TextField inputSurname;
    @Id("inputEmail")
    private TextField inputEmail;
    @Id("inputName")
    private TextField inputName;
    @Id("inputPassword")
    private PasswordField inputPassword;
    @Id("inputLogin")
    private TextField inputLogin;
    @Id("divOrderCourse")
    private Div divOrderCourse;


    @PostConstruct
    public void init() {
        languageGrid.addColumn(TeachingLanguages::getCode).setHeader("Kód jazyka").setSortable(true);
        languageGrid.addColumn(TeachingLanguages::getName).setHeader("Vyučovaný jazyk").setSortable(true);
        List<TeachingLanguages> languages = school.getAllLanguages();
        languageGrid.setItems(languages);

        selectLanguages.setItemLabelGenerator(TeachingLanguages::getName);
        selectLanguages.setItems(languages);
        selectLanguages.setValue(languages.get(0));

        orderCourseButton.addClickListener(orderCourseListener());

        if (authentication.get().isPresent()) {
            divOrderCourse.addClassNames("hide");
        }
    }

    private ComponentEventListener<ClickEvent<Button>> orderCourseListener() {
        return event -> {
            User user = new User();
            user.setName(inputName.getValue());
            user.setSurname(inputSurname.getValue());
            user.setRole("user");
            user.setHashedPassword(LoginService.getHashedPassword(inputPassword.getValue()));
            user.setEmail(inputEmail.getValue());
            user.setUsername(inputLogin.getValue());

            try {
                school.saveUser(user);
            } catch (UserException e) {
                Dialog dialog = new Dialog();
                dialog.add(new Text(e.getMessage()));
                dialog.open();
                return;
            } catch (Exception e) {
                Dialog dialog = new Dialog();
                dialog.add(new Text("Tento uživate databázi již existuje."));
                dialog.open();
                return;
            }
            User savedUser = school.getUserByName(user.getUsername());

            Course course = new Course();
            course.setDescription("Kurz pro uživatele " + user.getName() + " " + user.getSurname());
            course.setPoints(0);
            course.setNativeLang("cz");
            course.setIdTeachingLanguage(selectLanguages.getValue().getIdTeachingLanguage());
            course.setIdUser(savedUser.getIdUser());

            try {
                school.saveCourse(course);
            } catch (CourseException e) {
                Dialog dialog = new Dialog();
                dialog.add(new Text(e.getMessage()));
                dialog.open();
                return;
            } catch (Exception e) {
                Dialog dialog = new Dialog();
                dialog.add(new Text("Tento kurz databázi již existuje." + e));
                dialog.open();
                return;
            }

            Course savedCourse = school.getCourseByUserAndLanguage(savedUser.getIdUser(), course.getIdTeachingLanguage());
            for (Test test: school.getAllTestsByLanguage(savedCourse.getIdTeachingLanguage())) {
                CourseToTest testToCourse = new CourseToTest();
                testToCourse.setDone(0);
                testToCourse.setIdTest(test.getIdTest());
                testToCourse.setIdCourse(savedCourse.getIdCourse());

                try {
                    CourseToTest c2t = school.saveCourseToTest(testToCourse);
                } catch (CourseToTestException e) {
                    Dialog dialog = new Dialog();
                    dialog.add(new Text(e.getMessage()));
                    dialog.open();
                    return;
                } catch (Exception e) {
                    Dialog dialog = new Dialog();
                    dialog.add(new Text("Chyba při vytváření testů. Pro tento kurz." + e));
                    dialog.open();
                    return;
                }
            }

            Notification notification = Notification.show("Kurz byl úspěšně vytvořen prosím přihlašte se!");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        };
    }

    /**
     * Creates a new HomeForm.
     */
    public HomeForm() {
        // You can initialise any data required for the connected UI components here.
    }

}


