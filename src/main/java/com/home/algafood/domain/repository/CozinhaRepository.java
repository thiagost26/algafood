package com.home.algafood.domain.repository;

import java.util.List;

import com.home.algafood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> todas();
	Cozinha buscar(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Cozinha cozinha);

}
