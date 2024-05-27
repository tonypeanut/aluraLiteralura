package com.tonypeanut.literalura.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tonypeanut.literalura.repository.*;
import com.tonypeanut.literalura.service.ConsumoAPI;
import com.tonypeanut.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class App {
    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private TodosIdiomasRepository todosIdiomasRepository;



    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public App(
            LibroRepository libroRepository,
            AutorRepository autorRepository,
            TodosIdiomasRepository todosIdiomasRepository){

        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.todosIdiomasRepository = todosIdiomasRepository;
    }

    private void muestraMenu(){
        System.out.println("""
                Elija la opción a través de su número:
                1.- Buscar libro por título
                2.- Listar libros registrados
                3.- Listar autores registrados
                4.- Listar autores vivos en un determinado año
                5.- Listar libros por idioma
                
                0.- Salir
                """);
    }

    private int recibirOpcion(Integer valorMaximoPermitido){
        var opcion = input.nextLine();

        try{
            var opcionInt = Integer.parseInt(opcion);
            if (opcionInt <= valorMaximoPermitido && opcionInt >= 0){
                return opcionInt;
            } else {
                throw new Exception("Opción no válida");
            }

        }catch (Exception e){
            System.out.println("Opción no válida. Prueba nuevamente");
            return -1;
        }
    }

    private void ejecutarOpcionMenu(Integer opcion) throws InterruptedException {
        switch (opcion){
            case 1:
                buscarNuevoLibro();
                break;
            case 2:
                listarTodosLosLibros();
                break;
            case 3:
                listarTodosLosAutores();
                break;
            case 4:
                listarTodosLosAutoresVivos();
                break;
            case 5:
                listarLibrosPorIdioma();
                break;
            default:
                break;
        }
    }

    private void buscarNuevoLibro() throws InterruptedException {
        System.out.println("Ingresa el nombre del libro que deseas buscar y agregar a la base de datos:");
        var buscar = input.nextLine();

        System.out.println("Buscando Libro en base de datos local....");

        //Buscamos el libro en la base de de datos local
        var libroInterno = libroRepository.buscarLibroPorTitulo(buscar);

        // Si el libro no existe en la base de datos local, se buscará en Gutendex.
        if (libroInterno.isEmpty()) {
            System.out.println("Libro no existente en la base de datos local, buscando externamente....");
            String URL = URL_BASE + buscar.replace(" ", "%20");
            String response = consumoApi.obtenerDatos(URL);

            DatosGutendex gutendex = conversor.obtenerDatos(response, DatosGutendex.class);

            // Si el libro existe en gutendex se obtiene el primer libro del resultado, en caso de no existir se notifica al usuario.
            if (!gutendex.results().isEmpty()) {

                Libro libro = new Libro(gutendex.results().get(0));

                Autor autor = libro.getAutor();

                // Si el Id del autor es "null", se procee a buscar en la base de datos o a registrarlo.
                if (autor.getId() == null) {
                    // Se busca el autor del libro en la base de datos.
                    var autoresBuscados = autorRepository.buscarAutorPorNombre(autor.getName());

                    // Si el autor no existe en la base de datos se agrega, si ya existe se recupera de la base de datos.
                    if (autoresBuscados.isEmpty()) {
                        autor = autorRepository.save(autor);
                    } else {
                        autor = autoresBuscados.get(0);
                    }
                }

                libro.setAutor(autor);
                libroRepository.save(libro);

                System.out.println("Libro encontrado");
                System.out.println(libro);
                System.out.println("Libro guardado con éxito");
                Thread.sleep(2000);
            } else {
                System.out.println("No se encontró el libro solicitado.");
            }
        } else {
            System.out.println(libroInterno.get(0));
            System.out.println("El libro ya existía en la base de datos.");
        }
    }

    private void listarTodosLosLibros(){
        var libros = libroRepository.listarTodosLosLibros();
        libros.forEach(libro ->{
            System.out.println(libro.getId());
            System.out.println(libro);
        });
    }

    private void listarTodosLosAutores(){
        var autores = autorRepository.listarTodosLosAutores();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Los autores registrados en la base de datos son: \n");
        autores.forEach(System.out::println);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void listarTodosLosAutoresVivos(){
        System.out.println("Ingresa el año del cual quieres saber que autores estaban vivos: ");
        var year = input.nextLine();

        try {
            var yearInt = Long.valueOf(year);
            var autores = autorRepository.listarTodosLosAutoresVivos(yearInt);
            if (autores.isEmpty()){
                System.out.println("No hay autores vivos en el año " + yearInt + " registrados en la base de datos.");
            } else {
                autores.forEach(System.out::println);
            }
        } catch (Exception e){
            System.out.println("Fecha no válida.");
        }
    }

    private void listarLibrosPorIdioma(){
        System.out.println("A continuación se muestra una lista con los idiomas que se encuentran registrados en la base de dato.");
        System.out.println("Para realizar la busqueda ingresa las 2 letras que representan el idioma:");

        var idiomas = libroRepository.listarTodosLosLenguajes();

        idiomas.forEach(e->{
            var idioma = todosIdiomasRepository.obtenerIdioma(e);
            System.out.println(idioma.toString().replace("[","").replace("]",""));
        });

        var idiomaSeleccionado = input.nextLine().toLowerCase();
        var listaFiltrado = idiomas.stream().filter(idiomaSeleccionado::equals).toList();
        if(listaFiltrado.isEmpty()){
            System.out.println("El idioma ingresado no es válido.");
        } else {
            var libros = libroRepository.listarLibrosPorIdioma(idiomaSeleccionado);
            System.out.println(libros);
        }
    }

    public void inicia() throws InterruptedException, JsonProcessingException {
        System.out.println("---------------------------------------");
        System.out.println("Bienvenido a LiterAlura");
        System.out.println("El mejor sistema para la gestión de tus libros preferidos");
        System.out.println("---------------------------------------");

        var verificarIdiomas = todosIdiomasRepository.verificarExistenciaDatos();

        if (verificarIdiomas.isEmpty()){
            todosIdiomasRepository.inicializarTabla();
        }

        while(true){

            muestraMenu();

            var opcion = recibirOpcion(5);

            if (opcion == 0){
                break;
            }

            ejecutarOpcionMenu(opcion);

            System.out.println("---------------------------------\n\n");
            Thread.sleep(1000);

        }

    }
}
