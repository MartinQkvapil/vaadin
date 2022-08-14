package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.Test;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.security.Authentication;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * A Designer generated component for the dashboard-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("dashboard-form")
@JsModule("./views/dashboard-form.ts")
public class DashboardForm extends LitTemplate {

    @Autowired
    private LanguageSchool school;

    @Autowired
    private Authentication authentication;

    @Id("chartAnswers")
    private Chart chartAnswers;
    @Id("gridTests")
    private Grid<Test> gridTests;

    @PostConstruct
    public void init() {
        Optional<User> optionalUser = authentication.get();
        if (optionalUser.isPresent()) {
            User loggedInUser = optionalUser.get();
            gridTests.addColumn(Test::getIdTest).setHeader("ID testu").setSortable(true);
            gridTests.addColumn(Test::getQuestionCount).setHeader("Počet otázek").setSortable(true);
            List<Test> tests = school.getAllTestsByUserId(loggedInUser.getIdUser());
            gridTests.setItems(tests);
        }

    }

    /**
     * Creates a new DashboardForm.
     */
    public DashboardForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
