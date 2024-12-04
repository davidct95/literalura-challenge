package com.alura.literalura.model;

import com.alura.literalura.dto.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private List<String> languages;
    private boolean copyright;
    private String media_type;
    private int download_count;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Author> authors = new ArrayList<>();

    public Book() {
    }

    public Book(String title, List<String> languages, boolean copyright, String media_type, int download_count) {
        this.title = title;
        this.languages = languages;
        this.copyright = copyright;
        this.media_type = media_type;
        this.download_count = download_count;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public int getDownload_count() {
        return download_count;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthors(Author author) {
        this.authors.add(author);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("----- LIBRO -----" + "\n");
        sb.append("Titulo: " + title + "\n");

        Author author = authors.get(0);
        AuthorDTO authorDTO = new AuthorDTO(author.getName(), author.getBirthDay(), author.getDeathDay());

        sb.append("Autor: " + authorDTO.name() + "\n");
        sb.append("Idioma: " + languages.get(0) + "\n");
        sb.append("Número de descargas: " + download_count + "\n");



        return sb.toString();

    }
}
