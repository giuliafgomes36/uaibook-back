package com.backend.uaibook.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Nome vazio")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "ISBN vazio")
    @Column(name = "isbn")
    private String isbn;

    @NotEmpty(message = "Autores vazio")
    @Column(name = "authors")
    private String authors;

    @NotNull(message = "Ano vazio")
    @Column(name = "year")
    private Integer year;

    @Column(name = "categories")
    @ElementCollection(targetClass = Category.class)
    @Enumerated(EnumType.STRING)
    private List<Category> categories;

    @Min(0)
    @Column(name = "amount")
    private Integer amount;

    @NotEmpty(message = "Editora vazia")
    @Column(name = "publisher")
    private String publisher;
}
