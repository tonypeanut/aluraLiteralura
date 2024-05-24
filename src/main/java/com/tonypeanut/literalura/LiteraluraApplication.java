package com.tonypeanut.literalura;

import com.tonypeanut.literalura.model.App;
import com.tonypeanut.literalura.model.Lenguaje;
import com.tonypeanut.literalura.repository.AutorRepository;
import com.tonypeanut.literalura.repository.LenguajesRepository;
import com.tonypeanut.literalura.repository.LibroRepository;
import com.tonypeanut.literalura.repository.TodosIdiomasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LenguajesRepository lenguajesRepository;

	@Autowired
	private TodosIdiomasRepository todosIdiomasRepository;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App aplicacion = new App(libroRepository, autorRepository, lenguajesRepository, todosIdiomasRepository);

		aplicacion.inicia();
	}
}
