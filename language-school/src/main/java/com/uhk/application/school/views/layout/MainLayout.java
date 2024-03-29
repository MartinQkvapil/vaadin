package com.uhk.application.school.views.layout;

import com.uhk.application.school.controller.LanguageSchool;
import com.uhk.application.school.model.entity.TeachingLanguages;
import com.uhk.application.school.model.entity.User;
import com.uhk.application.school.model.security.AuthenticationService;
import com.uhk.application.school.model.service.TeachingLanguageService;
import com.uhk.application.school.views.createquestion.CreateQuestionView;
import com.uhk.application.school.views.createtest.CreateTestView;
import com.uhk.application.school.views.dashboard.DashboardView;
import com.uhk.application.school.views.home.HomeView;
import com.uhk.application.school.views.manageusers.ManageUsersView;
import com.uhk.application.school.views.quiz.QuizView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The main view is a top-level placeholder for other views.
 */
@PageTitle("Main")
@CssImport("./styles/styles.css")
public class MainLayout extends AppLayout {
    private AuthenticationService authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public static class MenuItemInfo {
        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }



    public MainLayout(AuthenticationService authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        addToNavbar(createHeaderContent());
    }

    private Component createHeaderContent() {
        Header header = new Header();
        header.addClassNames("header-color", "bg-base", "border-b", "border-contrast-10", "box-border", "flex", "flex-col", "w-full");

        Div layout = new Div();
        layout.addClassNames("flex", "h-xl", "items-center", "px-l");

        Image img = new Image("images/lang-logo.png", "placeholder plant");
        img.setWidth("30px");
        layout.add(img);

        H1 appName = new H1("JAZYKOVÁ ŠKOLA - Angličtina, Španělština, Němčina");
        appName.addClassNames("title", "my-xs", "me-auto", "text-l");
        layout.add(appName);

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName(), "images/user.png");
            avatar.addClassNames("me-xs");

            ContextMenu userMenu = new ContextMenu(avatar);
            userMenu.setOpenOnClick(true);
            userMenu.addItem("Logout", e -> {
                authenticatedUser.logout();
            });

            Span name = new Span(user.getName() + " " + user.getSurname());
            name.addClassNames("font-medium", "text-s", "text-secondary");

            layout.add(avatar, name);
        } else {
            Anchor loginLink = new Anchor("login", "Přihlásit se");
            loginLink.addClassNames("login-link");
            layout.add(loginLink);
        }

        Nav nav = new Nav();
        nav.addClassNames("flex", "gap-s", "overflow-auto", "px-m");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("menu", "flex", "list-none", "m-1", "p-0");
        nav.add(list);

        for (RouterLink link : createLinks()) {
            ListItem item = new ListItem(link);
            item.addClassNames("menu-item");
            list.add(item);
        }

        header.add(layout, nav);
        return header;
    }

    private List<RouterLink> createLinks() {
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                new MenuItemInfo("Home", "la la-home", HomeView.class), //

                new MenuItemInfo("Dashboard", "lab la-dashcube", DashboardView.class), //

                new MenuItemInfo("Quiz", "la la-file", QuizView.class), //

                new MenuItemInfo("Create Test", "la la-book-open", CreateTestView.class), //

                new MenuItemInfo("Create Question", "la la-file", CreateQuestionView.class), //

                new MenuItemInfo("Manage Users", "la la-user-circle", ManageUsersView.class), //

        };
        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            if (accessChecker.hasAccess(menuItemInfo.getView())) {
                links.add(createLink(menuItemInfo));
            }

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "h-m", "items-center", "px-s", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());

        Span icon = new Span();
        icon.addClassNames("me-s", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s", "whitespace-nowrap");

        link.add(icon, text);
        return link;
    }

}
