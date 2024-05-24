package com.tonypeanut.literalura.repository;

import com.tonypeanut.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query ("SELECT l FROM Libro l WHERE l.gutendexId = :gutendexId")
    List<Libro> buscarPorGutendexId(Long gutendexId);

    @Query ("SELECT l FROM Libro l")
    List<Libro> listarTodosLosLibros();

    @Query ("SELECT l FROM Libro l JOIN l.languages i WHERE i.lenguaje = :idioma")
    List<Libro> listarLibrosPorIdioma(String idioma);

}
