package com.tonypeanut.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(String name){
        this.name = name;
    }

    public Autor(String name, Integer birth_year, Integer death_year) {
        this.name = name;
        this.birthYear = birth_year;
        this.deathYear = death_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birthYear;
    }

    public void setBirth_year(Integer birth_year) {
        this.birthYear = birth_year;
    }

    public Integer getDeath_year() {
        return deathYear;
    }

    public void setDeath_year(Integer death_year) {
        this.deathYear = death_year;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String texto;

        if (birthYear == null && deathYear == null){
            texto = name + " (sin fecha)";
        } else {
            texto = name + " (" + birthYear + " - " + deathYear + ")";
        }
        return texto;
    }
}
