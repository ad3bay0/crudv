package com.ad3bay0.crudv.demo.views;

import com.ad3bay0.crudv.demo.models.Customer;
import com.ad3bay0.crudv.demo.repo.CustomerRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import org.springframework.util.StringUtils;

/**
 * MainView
 */

@Route
public class MainView extends VerticalLayout {

    private final CustomerRepository repo;
    final Grid<Customer> grid;
    private final CustomEditor editor;
    final TextField filter;
    private final Button addNewBtn;

    public MainView(CustomerRepository repo, CustomEditor editor) {

        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Customer.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Customer", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {

            editor.editCustomer(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));

        editor.setChangeHandler(() -> {

            editor.setVisible(false);

            listCustomers(filter.getValue());

        });

        listCustomers(null);

    }

    private void listCustomers(String filterText) {

        if (StringUtils.isEmpty(filterText)) {

            grid.setItems(repo.findAll());

        } else {

            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));

        }

    }

}