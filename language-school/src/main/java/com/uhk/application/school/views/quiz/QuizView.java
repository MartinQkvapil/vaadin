package com.uhk.application.school.views.quiz;

import com.uhk.application.school.views.components.QuizForm;
import com.uhk.application.school.views.layout.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

@PageTitle("Quiz")
@Route(value = "quiz", layout = MainLayout.class)
@RolesAllowed("user")
public class QuizView extends QuizForm {}
