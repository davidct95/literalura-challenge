package com.alura.literalura.repository;

import com.alura.literalura.dto.AuthorDTO;
import com.alura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(String name);

    @Query("SELECT a FROM Author a WHERE :anio BETWEEN a.birthDay AND a.deathDay")
    List<Author> findByAuthorByAnio(@Param("anio") int anio);
}
