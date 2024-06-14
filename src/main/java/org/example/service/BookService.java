package org.example.sandbox.service;

import org.example.sandbox.dto.BookDto;
import org.example.sandbox.entities.Book;
import org.example.sandbox.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {

//    public List<Book> getAll(){
//        List<Book> books = new ArrayList<>(List.of(
//                new Book(1l, "alice in wonderland", "amar hayam", "aversev")));
//        return books;
//    }

    BookRepository bookRepository;
    ModelMapper modelMapper;

    public BookService(BookRepository bookRepository,
                       ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<BookDto> getAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        List<BookDto> bookDtos = new ArrayList<>();
        books.forEach(book -> bookDtos.add(modelMapper.map(book, BookDto.class)));
        return bookDtos;
    }
}
