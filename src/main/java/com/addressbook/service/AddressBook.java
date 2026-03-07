package com.addressbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.addressbook.model.Person;

public class AddressBook {

	// List to store contacts
	List<Person> contactList = new ArrayList<>();

	// Add contact with duplicate check
	public void addContact(Person person) {

		boolean duplicate = contactList.stream().anyMatch(p -> p.getFirstName().equalsIgnoreCase(person.getFirstName())
				&& p.getLastName().equalsIgnoreCase(person.getLastName()));

		if (duplicate) {

			System.out.println("Duplicate contact found. Cannot add.");

		} else {

			contactList.add(person);
			System.out.println("Contact Added Successfully");
		}
	}

	// Edit contact using Stream API
	public void editContact(String name, String address, String city, String state) {

		Optional<Person> person = contactList.stream().filter(p -> p.getFirstName().equalsIgnoreCase(name)).findFirst();

		if (person.isPresent()) {

			person.get().setAddress(address);
			person.get().setCity(city);
			person.get().setState(state);

			System.out.println("Contact Updated Successfully");
		} else {
			System.out.println("Contact not found");
		}
	}

	// Delete contact using Stream API
	public void deleteContact(String name) {

		boolean removed = contactList.removeIf(p -> p.getFirstName().equalsIgnoreCase(name));

		if (removed)
			System.out.println("Contact Deleted Successfully");
		else
			System.out.println("Contact not found");
	}

	// Display contacts using Stream API
	public void displayContacts() {

		if (contactList.isEmpty())
			System.out.println("No contacts available");
		else
			contactList.stream().forEach(System.out::println);
	}
}