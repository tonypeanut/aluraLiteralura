package com.tonypeanut.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name="estantes")
public class Estante {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String estante;

    @ManyToOne
    private Libro libro;

    public Estante() {
    }

    public Estante(String estante) {
        this.estante = estante;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String stante) {
        this.estante = stante;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }


    @Override
    public String toString() {
        return estante;
    }
}
