package com.uhk.application.school.views.manageusers;

import com.uhk.application.school.views.components.LoginForm;
import com.uhk.application.school.views.layout.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Spravovat uživatele")
@Route(value = "manage-users", layout = MainLayout.class)
@RolesAllowed("admin")
public class ManageUsersView extends LoginForm {

//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {
//
//    }

//    public ManageUsersView() {
//        setSpacing(false);
//
//        Image img = new Image("images/empty-plant.png", "placeholder plant");
//        img.setWidth("200px");
//        add(img);
//
//        add(new H2("This place intentionally left empty"));
//        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
//
//        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.CENTER);
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        getStyle().set("text-align", "center");
//    }
}
