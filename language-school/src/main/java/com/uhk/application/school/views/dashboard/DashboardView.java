package com.uhk.application.school.views.dashboard;

import com.uhk.application.school.views.layout.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.RolesAllowed;

@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
@RolesAllowed("user")
public class DashboardView extends HorizontalLayout {}
