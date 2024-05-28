package com.tonypeanut.literalura.repository;

import com.tonypeanut.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query (value = "SELECT a FROM Autor a")
    List<Autor> listarTodosLosAutores();

    @Query (value = "SELECT DISTINCT ON (name) * FROM autores a WHERE a.birth_year <= :year AND a.death_year >= :year", nativeQuery = true)
    List<Autor> listarTodosLosAutoresVivos(Long year);

    List<Autor> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(Long Year, Long Year2);

    @Query ("SELECT a FROM Autor a WHERE a.name = :name")
    List<Autor> buscarAutorPorNombre(String name);

    @Query ("SELECT a FROM Autor a WHERE a.name ILIKE %:name%")
    List<Autor> buscarAutorPorNombreSimilar(String name);

    List<Autor> findByDeathYearGreaterThan(Long Year);

    List<Autor> findByBirthYearLessThan(Long Year);
}
