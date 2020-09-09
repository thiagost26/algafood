package com.home.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
	List<Cozinha> findByNome(String nome);

	List<Cozinha> findTodasByNomeContaining(String nome);
	
	boolean existsByNome(String nome);

}
