package com.alura.literalura.model;

import com.alura.literalura.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birthDay;
    private int deathDay;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors", cascade = CascadeType.MERGE)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String name, int birthDay, int deathDay) {
        this.name = name;
        this.birthDay = birthDay;
        this.deathDay = deathDay;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getDeathDay() {
        return deathDay;
    }

    public List<Book> getBooks() {
        return books;
    }

    // Getters, setters y métodos auxiliares
    public void addBook(Book book) {
        books.add(book);
        book.getAuthors().add(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.getAuthors().remove(this);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("-----AUTOR-----\n");
        sb.append("Nombre: " + getName() + "\n");
        sb.append("Año de nacimiento: " + getBirthDay() + "\n");
        sb.append("Año de fallecimiento: " + getDeathDay() + "\n");


        List<String> nameBooks = new ArrayList<>();

        for (Book book: books) {
            nameBooks.add(book.getTitle());
        }

        sb.append("Libros: " + nameBooks.toString() + "\n");

        return sb.toString();
     }
}
