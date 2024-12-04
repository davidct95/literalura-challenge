package com.alura.literalura.dto;

import com.alura.literalura.model.Book;

import java.util.List;

public record AuthorDTO(
        String name,
        int birthDay,
        int deathDay
) {
}
