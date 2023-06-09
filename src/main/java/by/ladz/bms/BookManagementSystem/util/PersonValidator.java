package by.ladz.bms.BookManagementSystem.util;



import by.ladz.bms.BookManagementSystem.models.Person;
import by.ladz.bms.BookManagementSystem.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Year;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(person.getYearOfBirth() > Year.now().getValue()){
            errors.rejectValue("yearOfBirth", "",
                    "The year of birth cannot be greater than the current year");
        }
        if(peopleService.findByFirstNameAndLastName(person.getFirstName(), person.getLastName()) != null){
            errors.rejectValue("lastName", "",
                    "There is already a person with this first name and last name");
            errors.rejectValue("firstName", "",
                    "There is already a person with this first name and last name");
        }
    }
}
