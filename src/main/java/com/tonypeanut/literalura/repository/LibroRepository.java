package com.tonypeanut.literalura.repository;

import com.tonypeanut.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query ("SELECT l FROM Libro l WHERE l.title ILIKE %:titulo%")
    List<Libro> buscarLibroPorTitulo(String titulo);

    @Query ("SELECT l FROM Libro l")
    List<Libro> listarTodosLosLibros();

    @Query ("SELECT l FROM Libro l WHERE l.lenguaje = :idioma")
    List<Libro> listarLibrosPorIdioma(String idioma);


    @Query (value = "SELECT DISTINCT lenguaje FROM libros", nativeQuery = true)
    List<String> listarTodosLosLenguajes();

}
