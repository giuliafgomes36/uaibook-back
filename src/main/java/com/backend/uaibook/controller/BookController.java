package com.backend.uaibook.controller;

import com.backend.uaibook.dto.Response;
import com.backend.uaibook.entity.Book;
import com.backend.uaibook.entity.Category;
import com.backend.uaibook.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public ResponseEntity<Response<Book>> createBook(@RequestBody @Valid Book book, BindingResult result) {
        Response<Book> response = new Response<>();
        if(result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setErrors(errors);

            return ResponseEntity.badRequest().body(response);
        }

        response.setData(bookService.createBook(book));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/book")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/book/categories")
    public List<String> getCategories() {
        return Category.getNames();
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Response<Book>> updateBook(@PathVariable(name = "id") Long id, @RequestBody @Valid Book book, BindingResult result) {
        Response<Book> response = new Response<>();
        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
            response.setErrors(errors);

            return ResponseEntity.badRequest().body(response);
        }
        response.setData(bookService.updateBook(id, book));
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable(name = "id") Long id) {
        try {
            bookService.deleteBook(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
