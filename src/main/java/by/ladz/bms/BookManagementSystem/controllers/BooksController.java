package by.ladz.bms.BookManagementSystem.controllers;


import by.ladz.bms.BookManagementSystem.models.Book;
import by.ladz.bms.BookManagementSystem.models.Person;
import by.ladz.bms.BookManagementSystem.services.BooksService;
import by.ladz.bms.BookManagementSystem.services.PeopleService;
import by.ladz.bms.BookManagementSystem.util.BookValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) @Min(0) Integer page,
                        @RequestParam(value = "books_per_page", required = false) @Min(0) @Max(100) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear){
        if(page != null && booksPerPage != null){
            model.addAttribute("books", booksService.findAll(page, booksPerPage, sortByYear));
        } else {
            model.addAttribute("books", booksService.findAll(sortByYear));
        }
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("personWithBook", book.getPerson());
        if(book.getPerson() == null) {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/id";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("personWithBook", book.getPerson());
        return "books/edit";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "book_name", required = false) String bookName, Model model){
        if(bookName != null) {
            List<Book> foundedBooks = booksService.search(bookName);
            model.addAttribute("foundedBooks", foundedBooks);
        }
        return "books/search";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors())
            return "books/new";
        booksService.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if(bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign-book")
    public String assignBook(@PathVariable("id") int id, @ModelAttribute("personId") int personId){
        Book book = booksService.findById(id);
        booksService.assignBook(book, personId);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/unassign-book")
    public String unassignBook(@PathVariable("id") int id){
        Book book = booksService.findById(id);
        booksService.unassignBook(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }
}
