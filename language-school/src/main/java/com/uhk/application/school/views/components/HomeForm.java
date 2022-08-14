package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.security.UserDetailsServiceImpl;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
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


    @PostConstruct
    public void init() {
        languageGrid.addColumn(TeachingLanguages::getIdTeachingLanguage).setHeader("ID jazyka").setSortable(true);
        languageGrid.addColumn(TeachingLanguages::getName).setHeader("Vyučovaný jazyk").setSortable(true);
        List<TeachingLanguages> languages = school.getAllLanguages();
        languageGrid.setItems(languages);

        orderCourseButton.addClickListener(orderCourseListener());
    }

    private ComponentEventListener<ClickEvent<Button>> orderCourseListener() {
        return event -> {
            User user = new User();
            user.setName(inputName.getValue());
            user.setSurname(inputSurname.getValue());
            user.setRole("user");
            user.setHashedPassword(UserDetailsServiceImpl.getHashedPassword(inputPassword.getValue()));
            user.setEmail(inputEmail.getValue());
            user.setUsername(inputLogin.getValue());
            user.setIcon("");

            school.saveUser(user);
        };
    }

    /**
     * Creates a new HomeForm.
     */
    public HomeForm() {
        // You can initialise any data required for the connected UI components here.
    }

}


