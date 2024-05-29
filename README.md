<h1>Literalura</h1>
<p>Herramienta de consola desarrollada en Java que te permite gestionar tu propia biblioteca de libros.</p>
<p>Este desarrollo forma parte de la formación Java y Spring Boot G6 - ONE. </p>

<h2>Funciones</h2>
<p>La herramienta cuenta con 11 opciones que se listan acontinuación: </p>
<ul>
  <li><b>Opción: 1.- Buscar libro por título: </b>Esta opción solicita que ingreses el nombre de un libro. Primero realizará una consulta en la base de datos local, si el libro no existe consume datos de la api de Gutendex. Si encuentra el libro lo muestra en consola y lo guarda en la base de datos local en caso de no existir. </li>
  <li><b>Opción: 2.- Listar libros registrados: </b>Muestra todos los libros registrados en la base de datos local.</li>
  <li><b>Opción: 3.- Listar autores registrados: </b>Muestra todos los autores registrados en la base de datos local, con sus respectivos libros.</li>
  <li><b>Opción: 4.- Listar autores vivos en un determinado año: </b>Solicita al usuario que ingrese un año, a partir del cual consulta la base de datos y muestra únicamente los autores en los que el año ingresado este comprendido dentro del rango del año de nacimiento y año de fallecimiento del autor.</li>
  <li><b>Opción: 5.- Listar libros por idioma: </b>Muestra una lista con todos los idiomas registrados en la base de datos local, indicando el número de libros en cada idioma. Solicita al usuario ingresar un idioma por medio de las 2 letras que lo representan y a continuación lista todos los libros con el idioma seleccionado.</li>
  <li><b>Opción: 6.- Mostrar estadisticas de la base de datos:</b> Muestra estadisticas de la base de datos local que incluyen el total de libros registrados, para cada idioma muestra el total de libros, el promedio de descargas, el máximo de descargas y el minimo de descargas, también muestra los 10 libros más descargados.</li>
  <li><b>Opción: 7.- Mostrar 10 libros más descargados: </b>Muestra una lista con los 10 libros con más descargas registrados en la base de datos local.</li>
  <li><b>Opción: 8.- Buscar autor por nombre: </b> Solicita ingresar el nombre de un autor, busca en la base de datos local el autor con nombre más semejante al dato ingresado, si lo encuentra muestra al autor y todos sus libros.</li>
  <li><b>Opción: 9.- Listar autores que fallecieron despues de determinado año: </b> Muestra todos los autores de la base de datos local cuyos fecha de fallecimiento sea posterior al año ingresado.</li>
  <li><b>Opción: 10.- Listar autores que nacieron antes de determinado año: </b> Muestra todos los autores de la base de datos local cuyos fecha de nacimiento sea anterior al año ingresado.</li>
  <li><b>Opción: 0.- Salir: </b>termina la ejecución de la herramienta.</li>
</ul>


<h2>Tecnologías</h2>
<ul>
  <li>JDK 17</li>
  <li>Maven</li>
  <li>Spring Boot</li>
  <li>PostgreSQL</li>
  <li>Spring Data JPA</li>
  <li>Jackson</li>
</ul>

