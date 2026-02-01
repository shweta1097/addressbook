package com.vaadin.tutorial.addressbook;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.tutorial.addressbook.backend.Contact;
import com.vaadin.tutorial.addressbook.backend.ContactService;

@Route("")   // root path
@PageTitle("Addressbook")
@Theme(value = Lumo.class)
public class AddressbookView extends VerticalLayout {

    private TextField filter = new TextField("Filter contacts");
    private Grid<Contact> contactList = new Grid<>(Contact.class);
    private Button newContact = new Button("New contact");

    private ContactService service = ContactService.createDemoService();

    public AddressbookView() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        newContact.addClickListener(e -> {
            // open form dialog or editor
        });

        filter.addValueChangeListener(e -> refreshContacts(e.getValue()));

        contactList.setColumns("firstName", "lastName", "email");
        contactList.setItems(service.findAll(""));
    }

    private void buildLayout() {
        HorizontalLayout actions = new HorizontalLayout(filter, newContact);
        add(actions, contactList);
        setSizeFull();
    }

    private void refreshContacts(String filterText) {
        contactList.setItems(service.findAll(filterText));
    }
}
