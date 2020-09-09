package com.home.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.home.algafood.AlgafoodApplication;
import com.home.algafood.domain.model.Cozinha;
import com.home.algafood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
		
		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Francesa");
		
		System.err.println(cozinha.getNome());
		System.err.println(cozinha.getId());
		
		cozinhaRepository.save(cozinha);
	}

}
