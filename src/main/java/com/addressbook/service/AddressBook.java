package com.addressbook.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.addressbook.database.DatabaseConnection;
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
	
	//UC 17
	// Add contact to database
	public void addContactToDatabase(Person person) {

	    String sql = "INSERT INTO person_contact(first_name,last_name,address,city,state,zip,phone,email) VALUES (?,?,?,?,?,?,?,?)";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, person.getFirstName());
	        statement.setString(2, person.getLastName());
	        statement.setString(3, person.getAddress());
	        statement.setString(4, person.getCity());
	        statement.setString(5, person.getState());
	        statement.setString(6, person.getZip());
	        statement.setString(7, person.getPhoneNumber());
	        statement.setString(8, person.getEmail());

	        statement.executeUpdate();

	        System.out.println("Contact added to database");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//UC 18
	// Retrieve contacts from database
	public void getContactsFromDatabase() {

	    String sql = "SELECT * FROM person_contact";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet resultSet = statement.executeQuery()) {

	        while (resultSet.next()) {

	            String firstName = resultSet.getString("first_name");
	            String lastName = resultSet.getString("last_name");
	            String city = resultSet.getString("city");
	            String phone = resultSet.getString("phone");

	            System.out.println(firstName + " " + lastName + " | " + city + " | " + phone);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	// UC 19
	// Update contact in database
	public void updateContactInDatabase(String firstName, String newCity, String newPhone) {

	    String sql = "UPDATE person_contact SET city=?, phone=? WHERE first_name=?";

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, newCity);
	        statement.setString(2, newPhone);
	        statement.setString(3, firstName);

	        int rows = statement.executeUpdate();

	        if (rows > 0)
	            System.out.println("Contact updated successfully");
	        else
	            System.out.println("Contact not found");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}