package com.example.springboottest.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.tomcat.jni.Local;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.springboottest.domain.Book;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    private final static String BOOT_TEST_TITLE = "Spring Boot Test Book";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void bookSaveTest() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE+"1").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);

        Book book2 = Book.builder().title(BOOT_TEST_TITLE+"2").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        Book book3 = Book.builder().title(BOOT_TEST_TITLE+"3").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book3);

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList, hasSize(3));
        assertThat(bookList, contains(book1, book2, book3));
    }

    @Test
    public void bookSaveAndDeleteTest() {
        Book book1 = Book.builder().title(BOOT_TEST_TITLE+"1").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book1);

        Book book2 = Book.builder().title(BOOT_TEST_TITLE+"2").publishedAt(LocalDateTime.now()).build();
        testEntityManager.persist(book2);

        bookRepository.deleteAll();
        assertThat(bookRepository.findAll(), IsEmptyCollection.empty());
    }

}