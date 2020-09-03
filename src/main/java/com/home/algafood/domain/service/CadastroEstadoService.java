package com.home.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.home.algafood.domain.exception.NomeEstadoJaCadastradoException;
import com.home.algafood.domain.model.Estado;
import com.home.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	@Transactional
	public Estado salvar(Estado estado) {
		
		return estadoRepository.save(estado);
	}


	@Transactional
	public void excluir(Long estadoId) {
		
		try {
			estadoRepository.deleteById(estadoId);
			
		}  catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com código %d", estadoId));
		}
		
	}

}
