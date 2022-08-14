package com.uhk.application.school.views.createquestion;

import com.uhk.application.school.views.components.QuestionForm;
import com.uhk.application.school.views.layout.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

@PageTitle("Create Question")
@Route(value = "create-question", layout = MainLayout.class)
@RolesAllowed("admin")
public class CreateQuestionView extends QuestionForm {}
