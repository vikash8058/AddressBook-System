package com.addressbook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressbook.model.Person;
import com.addressbook.service.AddressBook;

@RestController
@RequestMapping("/contacts")
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
    @GetMapping
    public List<Person> getContacts() {

        return addressBook.getContacts();
    }
    
    // Add new contact
    @PostMapping
    public String addContact(@RequestBody Person person) {

        addressBook.addContact(person);

        return "Contact added successfully";
    }
    
 // Update contact
    @PutMapping("/{name}")
    public String updateContact(@PathVariable String name, @RequestBody Person updatedPerson) {

        List<Person> contacts = addressBook.getContacts();

        for (Person person : contacts) {

            if (person.getFirstName().equalsIgnoreCase(name)) {

                person.setAddress(updatedPerson.getAddress());
                person.setCity(updatedPerson.getCity());
                person.setState(updatedPerson.getState());

                return "Contact updated successfully";
            }
        }

        return "Contact not found";
    }
}