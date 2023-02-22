package com.backend.uaibook.service;

import com.backend.uaibook.entity.Book;
import com.backend.uaibook.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElseThrow(() -> new NullPointerException("Book not found") );
    }

    public List<Book> getBooksFromUser(String cpf) {
        return null;
    }

    public Book updateBook(Long id, Book book) {
        Book bookSaved = bookRepository.getBookById(id);

        if(bookSaved == null)
            throw  new NullPointerException("Book not found");

        if(book.getName() != null)
            bookSaved.setName(book.getName());

        if(book.getIsbn() != null)
            bookSaved.setIsbn(book.getIsbn());

        if(book.getAuthors() != null)
            bookSaved.setAuthors(book.getAuthors());

        if(book.getYear() != null)
            bookSaved.setYear(book.getYear());

        if(book.getAmount() != null)
            bookSaved.setAmount(book.getAmount());

        if(book.getPublisher() != null)
            bookSaved.setPublisher(book.getPublisher());

        if(book.getCategories() != null)
            bookSaved.setCategories(book.getCategories());

        return bookRepository.save(bookSaved);
    }

    public Book deleteBook(Long id) {
        Book bookSaved = bookRepository.getBookById(id);

        if(bookSaved == null)
            throw  new NullPointerException("Book not found");

        bookRepository.delete(bookSaved);

        return bookSaved;
    }
}
