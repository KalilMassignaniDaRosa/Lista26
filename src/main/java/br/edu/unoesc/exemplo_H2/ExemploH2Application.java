package br.edu.unoesc.exemplo_H2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import br.edu.unoesc.exemplo_H2.service.FuncionarioService;

@SpringBootApplication
public class ExemploH2Application {
	//@Value("${mensagem}")
	private String mensagem;
	
	//@Value("${ambiente}")
	private String ambiente;
	
	public static void main(String[] args) {
		
		SpringApplication.run(ExemploH2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(FuncionarioService servico) {
		return args -> {
			System.out.println(mensagem + " " + ambiente);
			
			servico.popularTabelaInicial();

			
			
		};
	}
}

