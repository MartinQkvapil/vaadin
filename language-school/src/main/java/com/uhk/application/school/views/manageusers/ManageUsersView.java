package com.uhk.application.school.views.manageusers;

import com.uhk.application.school.views.components.ManageUsersForm;
import com.uhk.application.school.views.layout.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Spravovat uživatele")
@Route(value = "manage-users", layout = MainLayout.class)
@RolesAllowed("admin")
public class ManageUsersView extends ManageUsersForm {

}
