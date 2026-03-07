package com.addressbook.service;

import com.addressbook.model.Person;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook {

	// List to store contacts
	ArrayList<Person> contactList = new ArrayList<>();

	// Method to add contact
	public void addContact(Person person) {
		contactList.add(person);
		System.out.println("Contact Added Successfully");
	}

	// Method to edit contact using first name
	public void editContact(String name) {

		Scanner scanner = new Scanner(System.in);

		for (Person person : contactList) {

			if (person.getFirstName().equals(name)) {

				System.out.print("Enter new Address: ");
				person.setAddress(scanner.nextLine());

				System.out.print("Enter new City: ");
				person.setCity(scanner.nextLine());

				System.out.print("Enter new State: ");
				person.setState(scanner.nextLine());

				System.out.println("Contact Updated Successfully");
				return;
			}
		}

		System.out.println("Contact not found");
	}
}