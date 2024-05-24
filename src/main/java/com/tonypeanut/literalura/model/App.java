package com.tonypeanut.literalura.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.tonypeanut.literalura.repository.AutorRepository;
import com.tonypeanut.literalura.repository.LenguajesRepository;
import com.tonypeanut.literalura.repository.LibroRepository;
import com.tonypeanut.literalura.repository.TodosIdiomasRepository;
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
    private LenguajesRepository lenguajesRepository;
    private TodosIdiomasRepository todosIdiomasRepository;



    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public App(
            LibroRepository libroRepository,
            AutorRepository autorRepository,
            LenguajesRepository lenguajesRepository,
            TodosIdiomasRepository todosIdiomasRepository){

        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
        this.lenguajesRepository = lenguajesRepository;
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
                6.- Test
                
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
            case 6:
                test();
                break;
            default:
                break;
        }
    }

    private void buscarNuevoLibro() throws InterruptedException {
        System.out.println("Ingresa el nombre del libro que deseas buscar y agregar a la base de datos:");
        var buscar = input.nextLine();

        System.out.println("Busqueda en proceso");

        String URL = URL_BASE + buscar.replace(" ","%20");
        String response = consumoApi.obtenerDatos(URL);
        DatosGutendex gutendex = conversor.obtenerDatos(response, DatosGutendex.class);

        if(!gutendex.getResults().isEmpty()){

            DatosLibro datosLibro = gutendex.getResults().get(0);
            Libro libro = new Libro(datosLibro);


            if (libroRepository.buscarPorGutendexId(libro.getGutendexId()).isEmpty()){
                libroRepository.save(libro);

                System.out.println("Libro encontrado");
                System.out.println(libro);
                System.out.println("Libro guardado con éxito");
                Thread.sleep(2000);
            } else {
                System.out.println("El libro ya existía en la base de datos.");
            }


        } else {
            System.out.println("No se encontró el libro solicitado.");
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
        System.out.println("Para realizar la busqueda ingresa el idioma:");

        var idiomas = lenguajesRepository.listarTodosLosLenguajes();
        System.out.println(idiomas);



        //System.out.println(todosIdiomas.getClass());

        var idiomaSeleccionado = input.nextLine().toLowerCase();
        var listaFiltrado = idiomas.stream().filter(e-> idiomaSeleccionado.equals(e.getLenguaje())).toList();
        if(listaFiltrado.isEmpty()){
            System.out.println("El idioma ingresado no es válido.");
        } else {
            var libros = libroRepository.listarLibrosPorIdioma(idiomaSeleccionado);
            System.out.println(libros);
        }
    }

    private void test(){
        long id = 545454;
        var resultado = libroRepository.buscarPorGutendexId(id);
        System.out.println(resultado);
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

            var opcion = recibirOpcion(6);

            if (opcion == 0){
                break;
            }

            ejecutarOpcionMenu(opcion);

            System.out.println("---------------------------------\n\n");
            Thread.sleep(1000);

        }

    }
}
