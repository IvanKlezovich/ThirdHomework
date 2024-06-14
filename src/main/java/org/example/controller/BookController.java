package org.example.sandbox.controller;

import org.example.sandbox.dto.BookDto;
import org.example.sandbox.entities.Book;
import org.example.sandbox.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/hw")
    public List<BookDto> index() {
        return bookService.getAllBooks();
    }
}
