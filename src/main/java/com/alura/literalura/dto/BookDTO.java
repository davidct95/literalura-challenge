package com.alura.literalura.dto;

import com.alura.literalura.model.Author;
import com.alura.literalura.model.DatosAuthor;

import java.util.List;

public record BookDTO(
        String title,
        List<String>languages,
        boolean copyright,
        String media_type,
        int download_count,
        List<AuthorDTO> authorsDTO
) {
}
