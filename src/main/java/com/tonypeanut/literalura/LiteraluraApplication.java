package com.tonypeanut.literalura;

import com.tonypeanut.literalura.model.App;
import com.tonypeanut.literalura.repository.*;
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
	private TodosIdiomasRepository todosIdiomasRepository;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		App aplicacion = new App(libroRepository, autorRepository, todosIdiomasRepository);

		aplicacion.inicia();
	}
}
