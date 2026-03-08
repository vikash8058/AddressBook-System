package com.addressbook.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.addressbook.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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

	
	// Return all contacts - UC8
	public List<Person> getContacts() {
		return contactList;
	}
	
	//UC-13
	// Write contacts to file
	public void writeContactsToFile() {

	    try (FileWriter writer = new FileWriter("addressbook.txt")) {

	        contactList.forEach(person -> {
	            try {
	                writer.write(person.toString() + "\n");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        });

	        System.out.println("Contacts written to file");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	// Read contacts from file
	public void readContactsFromFile() {

	    try (BufferedReader reader = new BufferedReader(new FileReader("addressbook.txt"))) {

	        String line;

	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	//UC-14
	// Write contacts to CSV file
	public void writeContactsToCSV() {

	    try (CSVWriter writer = new CSVWriter(new FileWriter("addressbook.csv"))) {

	        for (Person person : contactList) {

	            String[] data = {
	                    person.getFirstName(),
	                    person.getLastName(),
	                    person.getAddress(),
	                    person.getCity(),
	                    person.getState(),
	                    person.getZip(),
	                    person.getPhoneNumber(),
	                    person.getEmail()
	            };

	            writer.writeNext(data);
	        }

	        System.out.println("Contacts written to CSV file");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// Read contacts from CSV file
	public void readContactsFromCSV() {

	    try (CSVReader reader = new CSVReader(new FileReader("addressbook.csv"))) {

	        String[] line;

	        while ((line = reader.readNext()) != null) {

	            System.out.println(String.join(" | ", line));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//UC 15
	// Write contacts to JSON file
	public void writeContactsToJSON() {

	    ObjectMapper mapper = new ObjectMapper();

	    try {

	        mapper.writeValue(new File("addressbook.json"), contactList);

	        System.out.println("Contacts written to JSON file");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	// Read contacts from JSON file
	public void readContactsFromJSON() {

	    ObjectMapper mapper = new ObjectMapper();

	    try {

	        List<Person> persons = mapper.readValue(
	                new File("addressbook.json"),
	                new TypeReference<List<Person>>() {}
	        );

	        persons.forEach(System.out::println);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}