package com.backend.uaibook.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Person {

    private String name;

    @Column(unique = true)
    private String cpf;

    private String email;

    private String telephone;
}