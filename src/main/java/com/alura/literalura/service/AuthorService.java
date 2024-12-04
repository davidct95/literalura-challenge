package com.alura.literalura.service;

import com.alura.literalura.model.Author;
import com.alura.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public Author findAuthorByName(String name){
        return this.repository.findByName(name);
    }

    public void saveAuthor(Author author) {
        this.repository.save(author);
    }

    public List<Author> findAllAuthors() {
        return this.repository.findAll();
    }

    public List<Author> findByAuthorByAnio(int anio){
        return  this.repository.findByAuthorByAnio(anio);
    }

}
