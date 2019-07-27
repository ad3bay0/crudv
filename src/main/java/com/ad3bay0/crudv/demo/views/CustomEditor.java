package com.ad3bay0.crudv.demo.views;

import com.ad3bay0.crudv.demo.models.Customer;
import com.ad3bay0.crudv.demo.repo.CustomerRepository;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * CustomEditor
 */

@SpringComponent
@UIScope
public class CustomEditor extends VerticalLayout implements KeyNotifier {

    private final CustomerRepository repository;

    /**
     * currently edited customer
     */

    private Customer customer;

    /** fields to edit properties in Customer entry */
    TextField firstName = new TextField("first name");
    TextField lastName = new TextField("last name");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");

    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Customer> binder = new Binder<>(Customer.class);

    private ChangeHandler changeHander;

    @Autowired
    public CustomEditor(CustomerRepository repo) {

        repository = repo;

        add(firstName, lastName, actions);

        // bi//bind using naming convention

        binder.bindInstanceFields(this);
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.addClickListener(e -> delete());
        save.addClickListener(e -> save());
        cancel.addClickListener(e -> editCustomer(customer));

    }

    void delete() {

        repository.delete(customer);
        changeHander.onchange();
    }

    void save() {

        repository.save(customer);
        changeHander.onchange();
    }

    public final void editCustomer(Customer c) {

        if (c == null) {
            setVisible(false);
             return;
        }

        final boolean persisted = c.getId() !=null;

        if(persisted){

            customer = repository.findById(c.getId()).get();

        }else{

            customer = c;
        }

        cancel.setVisible(persisted);


        binder.setBean(customer);

        setVisible(true);

        firstName.focus();
    }

    public interface ChangeHandler {

        void onchange();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHander = h;
    }

}