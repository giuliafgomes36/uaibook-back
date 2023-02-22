package com.backend.uaibook.repository;

import com.backend.uaibook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookById(Long id);
}
