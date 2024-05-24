package com.tonypeanut.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gutendexId;
    private String title;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> authors;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Genero> subjects;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Estante> bookshelves;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lenguaje> languages;
    private boolean copyright;
    private int download_count;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.gutendexId = datosLibro.getId();
        this.title = datosLibro.getTitle();
        this.setAuthors(datosLibro.getAuthors());
        this.setSubjects(datosLibro.getSubjects().stream().map(Genero::new).toList());
        this.setBookshelves(datosLibro.getBookshelves().stream().map(Estante::new).toList());
        this.setLanguages(datosLibro.getLanguages().stream().map(Lenguaje::new).toList());
        this.copyright = datosLibro.isCopyright();
        this.download_count = datosLibro.getDownload_count();
    }

    @Override
    public String toString() {
        String autores;
        String generos;
        String estantes;
        String idiomas;

        if (authors.isEmpty()){
            autores = "sin datos";
        } else {
            autores = authors.toString();
        }

        if (subjects.isEmpty()){
            generos = "sin datos";
        } else {
            generos = subjects.toString();
        }

        if (bookshelves.isEmpty()){
            estantes = "sin datos";
        } else {
            estantes = bookshelves.toString();
        }

        if (languages.isEmpty()){
            idiomas = "sin datos";
        } else {
            idiomas = languages.toString();
        }


        var libro = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                "\nTitulo: " + title + '\'' +
                "\nAutores = " + autores +
                "\nGeneros = " + generos +
                "\nLibreros = " + estantes +
                "\nIdiomas = " + idiomas +
                "\nTotal descargas = " + download_count +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        return  libro.replace("[","").replace("]","").replace("null"," ? ");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGutendexId() {
        return gutendexId;
    }

    public void setGutendexId(Long gutendexId) {
        this.gutendexId = gutendexId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Autor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Autor> authors) {
        authors.forEach(e-> e.setLibro(this));
        this.authors = authors;
    }

    public List<Genero> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Genero> subjects) {
        subjects.forEach(e -> e.setLibro(this));
        this.subjects = subjects;
    }

    public List<Estante> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<Estante> bookshelves) {
        bookshelves.forEach(e-> e.setLibro(this));
        this.bookshelves = bookshelves;
    }

    public List<Lenguaje> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Lenguaje> languages) {
        languages.forEach(e-> e.setLibro(this));
        this.languages = languages;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }


}
