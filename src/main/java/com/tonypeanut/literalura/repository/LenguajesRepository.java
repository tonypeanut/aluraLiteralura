package com.tonypeanut.literalura.repository;

import com.tonypeanut.literalura.model.Autor;
import com.tonypeanut.literalura.model.Lenguaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LenguajesRepository extends JpaRepository<Lenguaje, Long> {
    @Query(value = "SELECT DISTINCT ON (lenguaje) * FROM lenguajes", nativeQuery = true)
    List<Lenguaje> listarTodosLosLenguajes();

}
