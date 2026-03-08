package com.addressbook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Person>> getContacts() {

        return ResponseEntity.ok(addressBook.getContacts());
    }

    // Add new contact
    @PostMapping
    public ResponseEntity<String> addContact(@RequestBody Person person) {

        addressBook.addContact(person);

        return ResponseEntity.status(201).body("Contact added successfully");
    }

    // Update contact
    @PutMapping("/{name}")
    public ResponseEntity<String> updateContact(@PathVariable String name,
                                                @RequestBody Person updatedPerson) {

        List<Person> contacts = addressBook.getContacts();

        for (Person person : contacts) {

            if (person.getFirstName().equalsIgnoreCase(name)) {

                person.setAddress(updatedPerson.getAddress());
                person.setCity(updatedPerson.getCity());
                person.setState(updatedPerson.getState());

                return ResponseEntity.ok("Contact updated successfully");
            }
        }

        return ResponseEntity.status(404).body("Contact not found");
    }

    // Delete contact
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteContact(@PathVariable String name) {

        boolean removed = addressBook.getContacts().removeIf(
                p -> p.getFirstName().equalsIgnoreCase(name));

        if (removed)
            return ResponseEntity.ok("Contact deleted successfully");
        else
            return ResponseEntity.status(404).body("Contact not found");
    }
}



