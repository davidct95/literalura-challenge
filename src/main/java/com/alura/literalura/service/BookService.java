package com.alura.literalura.service;

import com.alura.literalura.model.Book;
import com.alura.literalura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public void saveBook(Book book) {
        this.repository.save(book);
    }

    public List<Book> findAllBooks() {
        return this.repository.findAll();
    }

    public List<Book> findByLanguages(String language) {
        return this.repository.findByLanguages(language);
    }

}
