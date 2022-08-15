package com.uhk.application.school.views.createtest;

import com.uhk.application.school.views.components.ManageTestsForm;
import com.uhk.application.school.views.layout.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

@PageTitle("Create Test")
@Route(value = "create-test", layout = MainLayout.class)
@RolesAllowed("admin")
public class CreateTestView extends ManageTestsForm {}
