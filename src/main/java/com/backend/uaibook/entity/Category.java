package com.backend.uaibook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {
    FANTASIA("Fantasia"),
    MANGA("Mangá"),
    ACAO("Ação"),
    AVENTURA("Aventura"),
    ROMANCE("Romance"),
    SCIFI("Sci-fi"),
    INFANTIL("Infantil"),
    ADULTO("Adulto"),
    MISTERIO("Mistério");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public static List<String> getNames() {
        return Arrays.stream(Category.values()).map(Category::getName).toList();
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
