//package org.example.utils;
//
//import org.example.dto.BookDto;
//import org.example.entities.Author;
//import org.example.entities.Book;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.util.Random;
//
///**
// * The {@code MappingUtils} class provides utilities for mapping between {@link Book} and {@link BookDto} objects,
// * as well as between {@link Author} and {@link AuthorDto} objects.
// * This class implements the {@link Serializable} interface, allowing its state to be serialized.
// *
// * <p>The class contains static methods for mapping objects and uses random numbers
// * to set the price of a book in {@link BookDto}.
// *
// * @author  Klezovich Ivan
// * @version 1.0
// * @since 22
// */
//
//public class MappingUtils implements Serializable {
//
//    /**
//     * SerialVersionUID for serialization.
//     */
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * Random number generator.
//     */
//    private static final Random random = new Random();
//
//    /**
//     * Converts a {@link Book} object to a {@link BookDto} object.
//     *
//     * @param book the book object
//     * @return the book DTO object
//     * @throws NullPointerException if the provided {@code book} is null
//     */
//    public BookDto mapToBookDto(Book book) {
//        BookDto bookDto = new BookDto();
//        bookDto.setId(book.getId());
//        bookDto.setTitle(book.getTitle());
//        bookDto.setAuthor(book.getAuthor());
//        bookDto.setPublisher(book.getPublisher());
//        bookDto.setPrice(random.nextInt(10));
//        return bookDto;
//    }
//
//    /**
//     * Converts a {@link BookDto} object to a {@link Book} object.
//     *
//     * @param bookDto the book DTO object
//     * @return the book object
//     * @throws NullPointerException if the provided {@code bookDto} is null
//     */
//    public Book mapToBook(BookDto bookDto) {
//        Book book = new Book();
//        book.setId(bookDto.getId());
//        book.setTitle(bookDto.getTitle());
//        book.setAuthor(bookDto.getAuthor());
//        book.setPublisher(bookDto.getPublisher());
//        return book;
//    }
//
//    /**
//     * Converts an {@link Author} object to an {@link AuthorDto} object.
//     *
//     * @param author the author object
//     * @return the author DTO object
//     * @throws NullPointerException if the provided {@code author} is null
//     */
//    public AuthorDto mapToAuthorDto(Author author) {
//        AuthorDto authorDto = new AuthorDto();
//        authorDto.setId(author.getId());
//        authorDto.setFirstName(author.getFirstName());
//        authorDto.setLastName(author.getLastName());
//        authorDto.setAutobiography(author.getAutobiography());
//        authorDto.setDateOfBirth(author.getDateOfBirth());
//        //authorDto.setBooks(relationShipService.getAllBooks(authorDto));
//        return authorDto;
//    }
//
//    /**
//     * Converts an {@link AuthorDto} object to an {@link Author} object.
//     *
//     * @param authorDto the author DTO object
//     * @return the author object
//     * @throws NullPointerException if the provided {@code authorDto} is null
//     */
//    public Author mapToAuthor(AuthorDto authorDto) {
//        Author author = new Author();
//        author.setId(authorDto.getId());
//        author.setFirstName(authorDto.getFirstName());
//        author.setLastName(authorDto.getLastName());
//        author.setAutobiography(authorDto.getAutobiography());
//        author.setDateOfBirth(authorDto.getDateOfBirth());
//        return author;
//    }
//}
