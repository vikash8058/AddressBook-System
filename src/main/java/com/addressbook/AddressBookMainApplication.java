package com.addressbook;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addressbook.model.Person;
import com.addressbook.service.AddressBook;

@SpringBootApplication
public class AddressBookMainApplication {

	public static void main(String[] args) {

		SpringApplication.run(AddressBookMainApplication.class, args);

		Scanner scanner = new Scanner(System.in);
		AddressBook addressBook = new AddressBook();

		int choice;

		do {

			System.out.println("\n---- Address Book Menu ----");
			System.out.println("1. Add Contact");
			System.out.println("2. Display Contacts");
			System.out.println("3. Exit");

			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1 -> {

				// Take contact details
				System.out.print("Enter First Name: ");
				String firstName = scanner.nextLine();

				System.out.print("Enter Last Name: ");
				String lastName = scanner.nextLine();

				System.out.print("Enter Address: ");
				String address = scanner.nextLine();

				System.out.print("Enter City: ");
				String city = scanner.nextLine();

				System.out.print("Enter State: ");
				String state = scanner.nextLine();

				System.out.print("Enter Zip: ");
				String zip = scanner.nextLine();

				System.out.print("Enter Phone Number: ");
				String phone = scanner.nextLine();

				System.out.print("Enter Email: ");
				String email = scanner.nextLine();

				Person person = new Person(firstName, lastName, address, city, state, zip, phone, email);

				addressBook.addContact(person);
			}
			case 2 -> addressBook.displayContacts();

			case 3 -> System.out.println("Exiting Address Book");

			default -> System.out.println("Invalid choice");
			}

		} while (choice != 3);

		scanner.close();
	}
}