package com.alura.literalura.repository;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    @Query(value = "SELECT * FROM books b WHERE :language = ANY(b.languages)", nativeQuery = true)
    List<Book> findByLanguages(@Param("language") String language);
}
