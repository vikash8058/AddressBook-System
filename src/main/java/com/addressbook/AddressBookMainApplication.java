package com.addressbook;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addressbook.model.Person;
import com.addressbook.service.AddressBook;

@SpringBootApplication
public class AddressBookMainApplication {

	public static void main(String[] args) {

		SpringApplication.run(AddressBookMainApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		// Map to store multiple address books
		Map<String, AddressBook> addressBookMap = new HashMap<>();

		int choice;

		do {

			System.out.println("\n--- Address Book System ---");
			System.out.println("1. Create Address Book");
			System.out.println("2. Add Contact");
			System.out.println("3. Edit Contact");
			System.out.println("4. Delete Contact");
			System.out.println("5. Display Contacts");
			System.out.println("6. Search Person by City or State");
			System.out.println("7. View Persons by City");
			System.out.println("8. View Persons by State");
			System.out.println("9. Count Contacts by City");
			System.out.println("10. Count Contacts by State");
			System.out.println("11. Sort Contacts by Name");
			System.out.println("12. Sort Contacts by City");
			System.out.println("13. Sort Contacts by State");
			System.out.println("14. Sort Contacts by Zip");
			System.out.println("15. Write Contacts to File");
			System.out.println("16. Read Contacts from File");
			System.out.println("17. Write Contacts to CSV");
			System.out.println("18. Read Contacts from CSV");
			System.out.println("19. Write Contacts to JSON");
			System.out.println("20. Read Contacts from JSON");
			System.out.println("19. Exit");

			System.out.print("Enter choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {

			case 1 -> {

				// Create new address book
				System.out.print("Enter Address Book Name: ");
				String name = scanner.nextLine();

				addressBookMap.put(name, new AddressBook());

				System.out.println("Address Book Created");
			}

			case 2 -> {

				// Add contact
				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				AddressBook book = addressBookMap.get(bookName);

				if (book != null) {

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

					System.out.print("Enter Phone: ");
					String phone = scanner.nextLine();

					System.out.print("Enter Email: ");
					String email = scanner.nextLine();

					Person person = new Person(firstName, lastName, address, city, state, zip, phone, email);

					book.addContact(person);
				} else {
					System.out.println("Address Book not found");
				}
			}

			case 3 -> {

				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				AddressBook book = addressBookMap.get(bookName);

				if (book != null) {

					System.out.print("Enter First Name to edit: ");
					String name = scanner.nextLine();

					System.out.print("Enter new Address: ");
					String address = scanner.nextLine();

					System.out.print("Enter new City: ");
					String city = scanner.nextLine();

					System.out.print("Enter new State: ");
					String state = scanner.nextLine();

					book.editContact(name, address, city, state);
				}
			}

			case 4 -> {

				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				AddressBook book = addressBookMap.get(bookName);

				if (book != null) {

					System.out.print("Enter First Name to delete: ");
					String name = scanner.nextLine();

					book.deleteContact(name);
				}
			}

			case 5 -> {

				System.out.print("Enter Address Book Name: ");
				String bookName = scanner.nextLine();

				AddressBook book = addressBookMap.get(bookName);

				if (book != null)
					book.displayContacts();
			}

			case 6 -> {

				// Search by city or state
				System.out.print("Enter City or State: ");
				String searchValue = scanner.nextLine();

				addressBookMap.values().stream().flatMap(book -> book.getContacts().stream())
						.filter(person -> person.getCity().equalsIgnoreCase(searchValue)
								|| person.getState().equalsIgnoreCase(searchValue))
						.forEach(System.out::println);
			}

			case 7 -> {

				// Group persons by city
				Map<String, List<Person>> cityMap = addressBookMap.values().stream()
						.flatMap(book -> book.getContacts().stream()).collect(Collectors.groupingBy(Person::getCity));

				cityMap.forEach((city, persons) -> {
					System.out.println("\nCity: " + city);
					persons.forEach(System.out::println);
				});
			}

			case 8 -> {

				// Group persons by state
				Map<String, List<Person>> stateMap = addressBookMap.values().stream()
						.flatMap(book -> book.getContacts().stream()).collect(Collectors.groupingBy(Person::getState));

				stateMap.forEach((state, persons) -> {
					System.out.println("\nState: " + state);
					persons.forEach(System.out::println);
				});
			}

			case 9 -> {

				// Count persons by city
				Map<String, Long> cityCount = addressBookMap.values().stream()
						.flatMap(book -> book.getContacts().stream())
						.collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));

				cityCount.forEach((city, count) -> System.out.println(city + " : " + count));
			}

			case 10 -> {

				// Count persons by state
				Map<String, Long> stateCount = addressBookMap.values().stream()
						.flatMap(book -> book.getContacts().stream())
						.collect(Collectors.groupingBy(Person::getState, Collectors.counting()));

				stateCount.forEach((state, count) -> System.out.println(state + " : " + count));
			}

			case 11 -> {

				// Sort contacts by name
				addressBookMap.values().stream()
						.flatMap(book -> book.getContacts().stream())
						.sorted(Comparator.comparing(Person::getFirstName))
						.forEach(System.out::println);
			}
			
			case 12 -> {

			    // Sort contacts by city
			    addressBookMap.values().stream()
			            .flatMap(book -> book.getContacts().stream())
			            .sorted(Comparator.comparing(Person::getCity))
			            .forEach(System.out::println);
			}
			
			case 13 -> {

			    // Sort contacts by state
			    addressBookMap.values().stream()
			            .flatMap(book -> book.getContacts().stream())
			            .sorted(Comparator.comparing(Person::getState))
			            .forEach(System.out::println);
			}
			
			case 14 -> {

			    // Sort contacts by zip
			    addressBookMap.values().stream()
			            .flatMap(book -> book.getContacts().stream())
			            .sorted(Comparator.comparing(Person::getZip))
			            .forEach(System.out::println);
			}
			
			case 15 -> {

			    addressBookMap.values().forEach(book ->
			            book.writeContactsToFile());
			}

			case 16 -> {

			    addressBookMap.values().forEach(book ->
			            book.readContactsFromFile());
			}
			
			case 17 -> {

			    addressBookMap.values().forEach(book ->
			            book.writeContactsToCSV());
			}

			case 18 -> {

			    addressBookMap.values().forEach(book ->
			            book.readContactsFromCSV());
			}
			
			case 19 -> {

			    addressBookMap.values().forEach(book ->
			            book.writeContactsToJSON());
			}

			case 20 -> {

			    addressBookMap.values().forEach(book ->
			            book.readContactsFromJSON());
			}
			
			case 21 -> System.out.println("Exiting Program");

			default -> System.out.println("Invalid Choice");
			}

		} while (choice != 21);

		scanner.close();
	}
}