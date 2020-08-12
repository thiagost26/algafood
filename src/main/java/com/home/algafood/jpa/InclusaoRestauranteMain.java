package com.home.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.home.algafood.AlgafoodApplication;
import com.home.algafood.domain.model.Cozinha;
import com.home.algafood.domain.model.Restaurante;
import com.home.algafood.domain.repository.CozinhaRepository;
import com.home.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
					.web(WebApplicationType.NONE)
					.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Thai Goumet");
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Thai Delivery");
		
		Restaurante restaurante3 = new Restaurante();
		restaurante3.setNome("Tuk Tuk comida Indiana");
		
		restaurante1 = restauranteRepository.adicionar(restaurante1);
		restaurante2 = restauranteRepository.adicionar(restaurante2);
		restaurante3 = restauranteRepository.adicionar(restaurante3);
		
		System.err.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
		System.err.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());
		System.err.printf("%d - %s\n", restaurante3.getId(), restaurante3.getNome());
		
	}

}
