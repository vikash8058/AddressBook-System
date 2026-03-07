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
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to Address Book Program");

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

		AddressBook addressBook = new AddressBook();

		addressBook.addContact(person);

	}

}
