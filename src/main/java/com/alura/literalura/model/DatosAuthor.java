package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAuthor(
        @JsonAlias("name")
        String name,
        @JsonAlias("birth_year")
        int birthDay,
        @JsonAlias("death_year")
        int deathDay
) {
}
