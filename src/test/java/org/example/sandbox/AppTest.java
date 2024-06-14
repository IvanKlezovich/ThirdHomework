package org.example.sandbox;

import org.example.sandbox.config.DataBaseConfig;
import org.example.sandbox.config.SpringConfig;
import org.example.sandbox.entities.Book;
import org.example.sandbox.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
@Transactional
public class AppTest {

    private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);

    @Autowired
    BookRepository bookRepository;

    @Before
    @Rollback(false)
    public void setUp(){
        Book book = new Book(1L, "Alice in wonderland", "amar hayam", "aversev");
        Book book1 = new Book(1L, "Alice", "amar hayam", "aversev");
        Book book2 = new Book(1L, "Tom&Jerry", "amar hayam", "aversev");

        bookRepository.saveAll(Arrays.asList(book, book1, book2));
    }

    @Test
    public void testFindAll(){
        Iterable<Book> books = bookRepository.findAll();
        for (Book book : books) {
            LOG.info(book.toString());
        }
    }
}
