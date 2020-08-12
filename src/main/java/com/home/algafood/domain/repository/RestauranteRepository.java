package com.home.algafood.domain.repository;

import java.util.List;

import com.home.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todos();
	Restaurante adicionar(Restaurante restaurante);
	Restaurante buscar(Long id);
	void remover(Restaurante restaurante);
	
}
