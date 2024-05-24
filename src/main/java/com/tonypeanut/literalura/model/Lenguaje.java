package com.tonypeanut.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="lenguajes")
public class Lenguaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String lenguaje;

    @ManyToOne
    private Libro libro;

    public Lenguaje() {
    }

    public Lenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return lenguaje;
    }
}
