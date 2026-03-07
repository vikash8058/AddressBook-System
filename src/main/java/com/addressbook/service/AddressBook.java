package com.addressbook.service;

import com.addressbook.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AddressBook {

	// List to store contacts
	// List to store contacts
	List<Person> contactList = new ArrayList<>();

	// Method to add contact
	public void addContact(Person person) {
		contactList.add(person);
		System.out.println("Contact Added Successfully");
	}

	// Method to edit contact using first name
	public void editContact(String name) {

		Scanner scanner = new Scanner(System.in);

		// Find person using Stream API
		Optional<Person> personOptional = contactList.stream()
				.filter(person -> person.getFirstName().equalsIgnoreCase(name)).findFirst();

		if (personOptional.isPresent()) {

			Person person = personOptional.get();

			System.out.print("Enter new Address: ");
			person.setAddress(scanner.nextLine());

			System.out.print("Enter new City: ");
			person.setCity(scanner.nextLine());

			System.out.print("Enter new State: ");
			person.setState(scanner.nextLine());

			System.out.print("Enter new Zip: ");
			person.setZip(scanner.nextLine());

			System.out.print("Enter new Phone Number: ");
			person.setPhoneNumber(scanner.nextLine());

			System.out.print("Enter new Email: ");
			person.setEmail(scanner.nextLine());

			System.out.println("Contact Updated Successfully");

		} else {
			System.out.println("Contact not found");
		}
	}

	// UC:4- Method to delete contact using name
	public void deleteContact(String name) {

		// Find person using Stream API
		Optional<Person> personOptional = contactList.stream()
				.filter(person -> person.getFirstName().equalsIgnoreCase(name)).findFirst();

		if (personOptional.isPresent()) {

			contactList.remove(personOptional.get());
			System.out.println("Contact Deleted Successfully");

		} else {

			System.out.println("Contact not found");
		}
	}

	// Display contacts
	public void displayContacts() {

		if (contactList.isEmpty()) {
			System.out.println("No contacts available");
		} else {
			contactList.forEach(System.out::println);
		}
	}
}