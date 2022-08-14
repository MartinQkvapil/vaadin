package com.uhk.application.school.views.components;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.Question;
import com.uhk.application.school.model.entity.Role;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.ItemClickEvent;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * A Designer generated component for the manage-users-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("manage-users-form")
@JsModule("./views/manage-users-form.ts")
public class ManageUsersForm extends LitTemplate {
    @Autowired
    private LanguageSchool school;

    @Id("gridUsers")
    private Grid<User> gridUsers;
    @Id("selectRole")
    private Select<String> selectRole;
    @Id("buttonUpdate")
    private Button buttonUpdate;

    private User currentUser;

    @PostConstruct
    public void init() {
//        Arrays.asList(Role.values());Select<String> select = new Select<>();

        selectRole.setItems(Role.ADMIN.name().toLowerCase(), Role.USER.name().toLowerCase());
        selectRole.setValue(Role.USER.name().toLowerCase());

        gridUsers.addColumn(User::getName).setHeader("Jméno").setSortable(true);
        gridUsers.addColumn(User::getSurname).setHeader("Příjmení").setSortable(true);
        gridUsers.addColumn(User::getUsername).setHeader("Login").setSortable(true);
        gridUsers.addColumn(User::getRole).setHeader("Role").setSortable(true);
        List<User> users = school.getAllUsers();
        gridUsers.setItems(users);
        gridUsers.addItemClickListener(gridSelectUser());
        buttonUpdate.addClickListener(setSettingsForRole());
    }

    private void refreshUserGrid() {
        gridUsers.setItems(school.getAllUsers());
    }

    private ComponentEventListener<ItemClickEvent<User>> gridSelectUser() {
        return event -> {
            currentUser = event.getItem();
        };
    }

    private ComponentEventListener<ClickEvent<Button>> setSettingsForRole() {
        return event -> {
            if (currentUser != null) {
                try {
                    currentUser.setRole(selectRole.getValue());
                    school.saveUser(currentUser);
                    refreshUserGrid();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    /**
     * Creates a new ManageUsersForm.
     */
    public ManageUsersForm() {
        // You can initialise any data required for the connected UI components here.
    }

}
