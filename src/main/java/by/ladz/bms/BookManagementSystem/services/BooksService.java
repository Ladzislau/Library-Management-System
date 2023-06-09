package by.ladz.bms.BookManagementSystem.services;

import by.ladz.bms.BookManagementSystem.models.Book;
import by.ladz.bms.BookManagementSystem.models.Person;
import by.ladz.bms.BookManagementSystem.repositories.BooksRepository;
import by.ladz.bms.BookManagementSystem.repositories.PeopleRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BooksService {

    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    private final String sortField = "publicationYear";

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public List<Book> findAll(Boolean sortByYear){
        if(sortByYear != null && sortByYear)
            return booksRepository.findAll(Sort.by(Sort.Direction.ASC, sortField));
        return findAll();
    }

    public Page<Book> findAll(int page, int booksPerPage, Boolean sortByYear){
        if(sortByYear != null && sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage,
                    Sort.by(Sort.Direction.ASC, sortField)));
        return booksRepository.findAll(PageRequest.of(page, booksPerPage));
    }

    public List<Book> findBooksByPerson(Person person){
        List<Book> books = booksRepository.findBooksByPerson(person);
        for (Book book :
                books) {
           updateOverdueStatus(book);
        }
        return books;
    }

    public Book findById(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    public void save(Book book){
        booksRepository.save(book);
    }

    public void update(int id, Book updatedBook){
        Optional<Book> previousVersion = booksRepository.findById(id);
        if(previousVersion.isPresent()) {
            updatedBook.setId(id);
            updatedBook.setPerson(previousVersion.get().getPerson());
            updatedBook.setAssignedAt(previousVersion.get().getAssignedAt());
            booksRepository.save(updatedBook);
        }
    }

    public void assignBook(Book book, int personId){
        Optional<Person> optionalPerson = peopleRepository.findById(personId);
        Hibernate.initialize(optionalPerson);
        if(optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            book.setPerson(person);
            book.setAssignedAt(LocalDateTime.now());
            booksRepository.save(book);
        }
    }

    public void unassignBook(Book book){
        book.setPerson(null);
        book.setAssignedAt(null);
        booksRepository.save(book);
    }

    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public List<Book> search(String name){
        return booksRepository.findBooksByNameContainingIgnoreCase(name);
    }

    public void updateOverdueStatus(Book book){
        LocalDateTime assignedDateTime = book.getAssignedAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime overdueDateTime = assignedDateTime.plusDays(10);
        boolean overdue = currentDateTime.isAfter(overdueDateTime);
        book.setOverdue(overdue);
    }
}
