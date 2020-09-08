package com.home.algafood.jpa;

import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.home.algafood.AlgafoodApplication;
import com.home.algafood.domain.model.Restaurante;
import com.home.algafood.domain.repository.RestauranteRepository;

public class BuscaRestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
		
		RestauranteRepository cadastroRestaurante = applicationContext.getBean(RestauranteRepository.class);
		
		Optional<Restaurante> restaurante = cadastroRestaurante.findById(1L);
		
		System.err.println(restaurante.get().getNome());
	}

}
