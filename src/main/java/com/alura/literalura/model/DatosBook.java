package com.alura.literalura.model;

import com.alura.literalura.dto.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBook(
        @JsonAlias("title")
        String title,
        @JsonAlias("languages")
        List<String> languages,
        @JsonAlias("copyright")
        boolean copyright,
        @JsonAlias("media_type")
        String media_type,
        @JsonAlias("download_count")
        int download_count,
        @JsonAlias("authors")
        List<DatosAuthor> authors
        ) {}
