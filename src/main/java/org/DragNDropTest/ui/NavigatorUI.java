package org.DragNDropTest.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.DragNDropTest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@SpringUI
@Theme("valo")
public class NavigatorUI extends UI {

    private MainMenuForm mainMenuForm = new MainMenuForm();
    private AdminMenuForm adminMenuForm = new AdminMenuForm();
    private Navigator navigator;
    public static final String MAIN_MENU_FORM = "mainMenuForm";
    public static final String ADMIN_MENU_FORM = "adminMenuForm";
    public RestTemplate restTemplate = new RestTemplate();
    public EmployeeRepository employeeRepository;

    @Autowired
    public NavigatorUI(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected void init(VaadinRequest request) {
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("Alexander", "12345"));
        navigator = new Navigator(this, this);
        navigator.addView(MAIN_MENU_FORM, mainMenuForm);
        navigator.addView(ADMIN_MENU_FORM, adminMenuForm);
        navigator.navigateTo(ADMIN_MENU_FORM);
    }

    public Navigator getNavigator() {
        return navigator;
    }
}
