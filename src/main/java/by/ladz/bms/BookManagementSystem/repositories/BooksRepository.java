package by.ladz.bms.BookManagementSystem.repositories;

import by.ladz.bms.BookManagementSystem.models.Book;
import by.ladz.bms.BookManagementSystem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByPerson(Person person);

    List<Book> findBooksByNameContainingIgnoreCase(String name);

}
