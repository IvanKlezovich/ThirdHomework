package org.example.sandbox.repository;

import org.example.sandbox.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
