package com.addressbook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.model.Person;
import com.addressbook.service.AddressBook;

@RestController
public class AddressBookController {

    private AddressBook addressBook = new AddressBook();

    // Constructor
    public AddressBookController() {

        addressBook.addContact(
            new Person("Vikash","Prajapati","Bhopal","Bhopal","MP","462001","9999999999","vikash@gmail.com")
        );

        addressBook.addContact(
            new Person("Rahul","Sharma","Indore","Indore","MP","452001","8888888888","rahul@gmail.com")
        );
    }

    // Get all contacts
    @GetMapping("/contacts")
    public List<Person> getContacts() {

        return addressBook.getContacts();
    }
}