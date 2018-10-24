package org.DragNDropTest.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.DropMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.GridDragSource;
import com.vaadin.ui.components.grid.GridDropTarget;
import org.DragNDropTest.entity.Employee;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainMenuForm extends Panel implements View {

    private MenuBar menuBar = new MenuBar();
    private VerticalLayout vLayout = new VerticalLayout();
    private Label lbl = new Label();
    private Grid<Employee> employeeGrid = new Grid<Employee>("Пользователи");
    private Employee dragged = null;

    public void setLblTime(String currentTimeStr) {
        this.lbl.setCaption(currentTimeStr);
    }

    public MainMenuForm() {
        employeeGrid.addColumn(Employee::getFullName).setCaption("ФИО");
        Button addBtn = new Button("Добавить");
        Button changeBtn = new Button("Изменить");
        Button deleteBtn = new Button("Удалить", e->removeEmployee());
        HorizontalLayout hLayout = new HorizontalLayout(addBtn,changeBtn,deleteBtn);
        GridDragSource<Employee> source = new GridDragSource<>(employeeGrid);
        source.addGridDragStartListener(e -> {
            dragged = e.getDraggedItems().iterator().next();
        });

        GridDropTarget<Employee> target = new GridDropTarget<>(employeeGrid, DropMode.ON_TOP_OR_BETWEEN);
        target.addGridDropListener(e -> {
            String[] names = e.getDataTransferText().split("\n");
            for (String name: names) {
                Employee empl = new Employee(name);
                ((NavigatorUI) getUI()).employeeRepository.save(empl);
                refreshEmployeeGrid();
            }
        });
        vLayout.addComponents(lbl, employeeGrid,hLayout);
        this.setContent(vLayout);
    }

    private void removeEmployee() {

    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refreshEmployeeGrid();
    }

    private void refreshEmployeeGrid() throws RestClientException {
        employeeGrid.setItems(((NavigatorUI) getUI()).employeeRepository.findAll());
    }

}