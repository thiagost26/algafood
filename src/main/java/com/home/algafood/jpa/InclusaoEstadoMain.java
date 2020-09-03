package com.home.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.home.algafood.AlgafoodApplication;
import com.home.algafood.domain.model.Estado;
import com.home.algafood.domain.repository.EstadoRepository;

public class InclusaoEstadoMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
		
		Estado estado1 = new Estado();
		estado1.setNome("Acre");
		estado1.setSigla("AC");
		
		Estado estado2 = new Estado();
		estado2.setNome("Rondônia");
		estado2.setSigla("RO");
		
		estado1 = estadoRepository.save(estado1);
		estado2 = estadoRepository.save(estado2);
		
		System.err.printf("%d - %s\n", estado1.getId(), estado1.getNome());
		System.err.printf("%d - %s\n", estado2.getId(), estado2.getNome());
	}

}
