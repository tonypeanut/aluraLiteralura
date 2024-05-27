package com.tonypeanut.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todosIdiomas")
public class TodosIdiomas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idioma;
    private String iso_639_1;
    private String iso_639_2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getIso_639_2() {
        return iso_639_2;
    }

    public void setIso_639_2(String iso_639_2) {
        this.iso_639_2 = iso_639_2;
    }

    @Override
    public String toString() {
        return iso_639_1 + " - " + idioma;
    }
}
