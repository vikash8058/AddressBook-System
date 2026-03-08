# Address Book Management System

## Project Overview

The **Address Book Management System** is a Java-based application developed using **Core Java, Streams API, File IO, JDBC, MySQL, and Spring Boot REST APIs**.
The purpose of this project is to manage contact details efficiently while demonstrating various Java concepts and modern backend development practices.

The application allows users to create and manage address books, add and update contacts, search and sort records, store data in files, interact with a database, and expose the functionality through REST APIs.

This project was implemented step-by-step through **Use Cases (UC1–UC25)**, gradually enhancing the system from a simple console application to a fully functional RESTful service.

---

# Technologies Used

* **Java**
* **Java Collections & Streams API**
* **Spring Boot**
* **JDBC**
* **MySQL**
* **File IO**
* **OpenCSV**
* **Jackson (JSON processing)**
* **Maven**

---

# Project Structure

```
src/main/java
│
├── com.addressbook
│   ├── controller
│   │   └── AddressBookController
│   │
│   ├── database
│   │   └── DatabaseConnection
│   │
│   ├── model
│   │   └── Person
│   │
│   ├── service
│   │   └── AddressBook
│   │
│   └── AddressBookMainApplication
```

---

# Features Implemented

The development of this project followed a **Use Case based approach**, gradually adding new capabilities.

---

# Section 1 – Core Address Book Features

### UC1 – Create Contact

A `Person` class was created to store contact information such as:

* First Name
* Last Name
* Address
* City
* State
* Zip
* Phone Number
* Email

---

### UC2 – Add Contact

Users can add a new contact to the address book.

Contacts are stored using a **List collection**.

---

### UC3 – Edit Contact

Allows updating an existing contact’s details such as address, city, or state.

---

### UC4 – Delete Contact

Users can remove a contact from the address book using the person's name.

---

### UC5 – Add Multiple Contacts

The system supports adding multiple contacts using **Java Collections (ArrayList)**.

---

### UC6 – Multiple Address Books

Multiple address books can be created using a **Map structure**.

Example:

```
Map<String, AddressBook>
```

Each address book has a unique name.

---

### UC7 – Prevent Duplicate Contacts

The system checks for duplicate contacts using:

* `equals()` method
* Java **Stream API**

---

### UC8 – Search Contact by City or State

Users can search contacts across all address books by **city or state** using streams.

---

### UC9 – View Contacts by City or State

Contacts are grouped using:

```
Collectors.groupingBy()
```

This allows viewing people living in a specific city or state.

---

### UC10 – Count Contacts by City or State

The application counts contacts based on city or state using:

```
Collectors.counting()
```

---

### UC11 – Sort Contacts by Name

Contacts can be sorted alphabetically using:

```
Comparator.comparing()
```

---

### UC12 – Sort Contacts by City, State, or Zip

The application supports sorting contacts based on different fields such as:

* City
* State
* Zip Code

---

# Section 2 – File IO Operations

### UC13 – Read/Write AddressBook using File IO

Contacts can be stored in a **text file** and read back using:

* FileWriter
* FileReader
* BufferedReader

---

### UC14 – Read/Write using CSV

Contacts are stored in **CSV format** using the **OpenCSV library**.

Example file:

```
addressbook.csv
```

---

### UC15 – Read/Write using JSON

Contacts can be stored and retrieved from a **JSON file** using the **Jackson library**.

Example file:

```
addressbook.json
```

---

# Section 3 – Database Integration

### UC16 – Connect to MySQL Database

The application connects to a MySQL database using **JDBC**.

A database named:

```
addressbook_service
```

was created.

---

### UC17 – Insert Contact into Database

New contacts can be added to the database using:

```
PreparedStatement
```

---

### UC18 – Retrieve Contacts from Database

Contacts stored in the database can be retrieved using:

```
SELECT queries
ResultSet
```

---

### UC19 – Update Contact in Database

Existing contacts can be updated using **UPDATE SQL queries**.

---

### UC20 – Delete Contact from Database

Contacts can be deleted from the database using **DELETE SQL queries**.

---

# Section 4 – REST API Implementation

The project was extended to expose address book functionality through **REST APIs using Spring Boot**.

---

### UC21 – Expose AddressBook as REST API

A REST controller was created to expose contact data through HTTP endpoints.

Example endpoint:

```
GET /contacts
```

---

### UC22 – Add Contact using REST API

A **POST API** allows adding contacts via JSON request body.

Example:

```
POST /contacts
```

---

### UC23 – Update Contact using REST API

A **PUT API** allows updating existing contact details.

Example:

```
PUT /contacts/{name}
```

---

### UC24 – Delete Contact using REST API

A **DELETE API** allows removing a contact.

Example:

```
DELETE /contacts/{name}
```

---

### UC25 – Proper HTTP Responses

The REST APIs were improved using **ResponseEntity** to return appropriate HTTP status codes such as:

* 200 OK
* 201 Created
* 404 Not Found

---

# Database Table

```
CREATE TABLE person_contact(
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50),
last_name VARCHAR(50),
address VARCHAR(100),
city VARCHAR(50),
state VARCHAR(50),
zip VARCHAR(10),
phone VARCHAR(20),
email VARCHAR(100)
);
```

---

# Example REST Endpoints

| Method | Endpoint           | Description           |
| ------ | ------------------ | --------------------- |
| GET    | `/contacts`        | Retrieve all contacts |
| POST   | `/contacts`        | Add new contact       |
| PUT    | `/contacts/{name}` | Update contact        |
| DELETE | `/contacts/{name}` | Delete contact        |

---

# Conclusion

This project demonstrates how a simple console-based Java application can be progressively enhanced into a fully functional backend service.

It covers several important Java and backend development concepts including:

* Collections
* Streams API
* File Handling
* CSV and JSON processing
* JDBC and MySQL integration
* RESTful API development with Spring Boot

The Address Book Management System provides a complete learning journey from **Core Java fundamentals to modern backend API development**.

---
