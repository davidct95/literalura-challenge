package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAPI(
        @JsonAlias("count")
        int count,
        @JsonAlias("results")
        List<DatosBook> results
) {
}
