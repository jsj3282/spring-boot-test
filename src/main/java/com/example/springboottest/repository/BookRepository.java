package com.example.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottest.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
