package com.home.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.home.algafood.domain.model.Restaurante;
import com.home.algafood.domain.repository.CozinhaRepository;
import com.home.algafood.domain.repository.RestauranteRepository;
import com.home.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	
	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
		
		if(restauranteOptional.isPresent()) {
			return ResponseEntity.ok(restauranteOptional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = cadastroRestaurante.salvar(restaurante); 
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
		
	}
	
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId
				, @RequestBody Restaurante restaurante) {

		try {
			Restaurante restauranteAutal = restauranteRepository.findById(restauranteId).orElse(null);
			
			if(restauranteAutal != null) {
				BeanUtils.copyProperties(restaurante, restauranteAutal, "id");
				
				restauranteAutal = cadastroRestaurante.salvar(restauranteAutal);
				return ResponseEntity.ok(restauranteAutal);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
						.body(e.getMessage());
		}
		
	}	
	
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
				@RequestBody Map<String, Object> campos) {
		Restaurante restauranteAutal = restauranteRepository
						.findById(restauranteId).orElse(null);
		
		if(restauranteAutal == null) {
			return ResponseEntity.notFound().build();  
		}
		
		merge(campos, restauranteAutal);
		
		
		return atualizar(restauranteId, restauranteAutal);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteAutal) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		System.err.println(restauranteOrigem);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
//			System.err.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, restauranteAutal, novoValor);			
		});
	}
	
	
	

}
