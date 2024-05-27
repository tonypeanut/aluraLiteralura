package com.tonypeanut.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        Long id,
        String title,
        List<Autor> authors,
        List<String> languages,
        int download_count
) { }
