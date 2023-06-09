# Library Management System

This is an electronic book management system designed for a library. Librarians can register readers, edit and remove reader profiles, view their profiles, add books, edit book details, remove books, assign books to readers, and unassign books once they are returned to the library. The system has been developed using the Spring Boot framework, incorporating Spring MVC and Spring Data JPA for seamless database operations. Validation is performed using Hibernate Validator, and dynamic HTML pages are generated using Thymeleaf.

## Features:

- Register readers and manage their profiles.
- View and edit reader profiles.
- Add books to the library's collection.
- View and edit book details.
- Assign books to readers for borrowing.
- Unassign books when returned by readers.
- Pagination and sorting functionality for the books page, allowing users to navigate through multiple pages and sort books by year.
- Book search functionality, enabling users to search for books by title.
- Overdue book highlighting: If a reader fails to return a book within 10 days, it will be marked as overdue, and the reader's profile will be highlighted in red.
- Additional endpoints:
  - /people: Displays all readers and allows the addition of new readers.
  - /people/{id}: Displays a specific reader's information, including a list of borrowed books. This page also allows for reader deletion and editing.
  - /books: Shows all books, supports pagination, and sorting by year. Users can also add new books and perform a search on this page.
  - /books/search: Displays search results based on the provided book name, which is specified using the book_name request parameter. This page is also redirected to when using the search function on the /books page.
  - /books/{id}: Allows users to assign a book to a reader (if it's unassigned) or unassign a book (if it's assigned). Users can also edit book details and delete books from the database.

## Technologies used:

- Spring Boot - Spring Boot provides a powerful and efficient framework for developing Java applications with minimal configuration.
- Spring Data JPA - Spring Data JPA is a convenient abstraction layer that simplifies working with relational databases, providing support for CRUD operations and query generation.
- Hibernate Validator - Hibernate Validator is a powerful validation framework that ensures the integrity and validity of data by applying validation rules to entity fields.
- Thymeleaf - Thymeleaf is a modern server-side Java template engine that enables the creation of dynamic HTML pages with seamless integration with Spring MVC.

## Getting Started

To get started with My Project, follow these steps:

1. Clone the repository: `git clone https://github.com/Ladzislau/Library-Management-System.git`
2. Install the necessary dependencies.
3. Build and run the project.
