package com.ad3bay0.crudv.demo;

import com.ad3bay0.crudv.demo.models.Customer;
import com.ad3bay0.crudv.demo.repo.CustomerRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * MainView
 */

 @Route
public class MainView extends VerticalLayout {

    private final CustomerRepository repo;
    final Grid<Customer> grid;

    
    public MainView(CustomerRepository repo){

        this.repo = repo;
        this.grid = new Grid<>(Customer.class);
        add(grid);
        listCustomers();

    }

    private void listCustomers(){

        grid.setItems(repo.findAll());
    }

}