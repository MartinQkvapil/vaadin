package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.repository.TeachingLanguagesRepository;
import com.uhk.application.school.model.service.TeachingLanguageService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

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
    private TeachingLanguageService teachingLanguageService;

    @Id("vaadinButton")
    private Button vaadinButton;
    @Id("teaching-languages-select")
    private Select<TeachingLanguages> teachingLanguagesSelect;


    @PostConstruct
    public void init() {
        teachingLanguagesSelect.setItems(teachingLanguageService.findAll());
        teachingLanguagesSelect.setItemLabelGenerator(language -> language.getName() + "\r\n");
    }

    /**
     * Creates a new HomeForm.
     */
    public HomeForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
