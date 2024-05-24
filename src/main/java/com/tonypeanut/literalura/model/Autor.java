package com.tonypeanut.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private Integer birth_year;
    private Integer death_year;

    @ManyToOne
    private Libro libro;

    public Autor() {
    }

    public Autor(String name, Integer birth_year, Integer death_year) {
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }



    @Override
    public String toString() {
        String texto;

        if (birth_year == null && death_year == null){
            texto = name + "(sin fecha)";
        } else {
            texto = name + "(" + birth_year + " - " + death_year + ")";
        }
        return texto;
    }
}
