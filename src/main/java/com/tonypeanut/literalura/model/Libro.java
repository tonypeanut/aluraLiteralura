package com.tonypeanut.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long gutendexId;
    private String title;

    @ManyToOne
    private Autor autor;

    private String lenguaje;
    private int downloadCount;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        this.gutendexId = datosLibro.id();
        this.title = datosLibro.title();

        if (datosLibro.authors().isEmpty()){
            this.autor = new Autor("Sin nombre");
        } else {
            this.autor = datosLibro.authors().get(0);
        }


        this.lenguaje = datosLibro.languages().get(0);
        this.downloadCount = datosLibro.download_count();
    }

    @Override
    public String toString() {
        return  "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                "\nTitulo: " + title + '\'' +
                "\nAutor: " + autor +
                "\nIdioma: " + lenguaje +
                "\nTotal descargas: " + downloadCount +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public int getDownload_count() {
        return downloadCount;
    }

    public void setDownload_count(int download_count) {
        this.downloadCount = download_count;
    }
}
