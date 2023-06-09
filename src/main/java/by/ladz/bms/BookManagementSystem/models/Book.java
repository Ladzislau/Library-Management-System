package by.ladz.bms.BookManagementSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Size(min = 1, max = 200, message = "Name should be between 1 and 200 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 40, message = "The length of the author's name cannot be greater than 40")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Publication year cannot be less than 0")
    @Column(name = "publication_year")
    private int publicationYear;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "assigned_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignedAt;

    @Transient
    private boolean overdue;

    public Book(){}

    public Book(int id, String name, String author, int publicationYear, Person person) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publicationYear = publicationYear;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
}
