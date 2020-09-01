package com.home.algafood.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.home.algafood.domain.model.Estado;

@Repository
public interface EstadoRepository {
	
	List<Estado> listar();
	Estado adicionar(Estado estado);
	Estado buscar(Long id);
	void remover(Estado estado);

}
