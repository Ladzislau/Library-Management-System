USE library_bms;

CREATE TABLE if not exists person (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    birth_year int NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `first_name` (first_name, last_name),
    CONSTRAINT `person_chk_1` CHECK (birth_year between 1920 and year(sysdate()))
);

CREATE TABLE if not exists book (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(200) NOT NULL,
    author varchar(40) DEFAULT NULL,
    publication_year int DEFAULT NULL,
    person_id int DEFAULT NULL,
    assigned_at timestamp DEFAULT current_timestamp,
    PRIMARY KEY (id),
    KEY fk_book_person (person_id),
    CONSTRAINT `fk_book_person` FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE SET NULL,
    CONSTRAINT `book_chk_1` CHECK ((publication_year between 0 and year(sysdate())))
);
