package by.ladz.bms.BookManagementSystem.repositories;

import by.ladz.bms.BookManagementSystem.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Person findByFirstNameAndLastName(String firstName, String lastName);
}
