package com.home.algafood.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.home.algafood.domain.exception.EntidadeEmUsoException;
import com.home.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.home.algafood.domain.exception.NomeEstadoJaCadastradoException;
import com.home.algafood.domain.model.Cozinha;
import com.home.algafood.domain.model.Estado;
import com.home.algafood.domain.model.Restaurante;
import com.home.algafood.domain.repository.EstadoRepository;
import com.home.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Optional<Estado> estadoOptional = estadoRepository.findById(id);

		if(estadoOptional.isPresent()) {
			return ResponseEntity.ok(estadoOptional.get());			
		}
		
		return ResponseEntity.notFound().build();
				
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
		
		try {
			cadastroEstado.salvar(estado);
			return ResponseEntity.ok().build();
		} catch (NomeEstadoJaCadastradoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
		
	}
	
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId
			, @RequestBody Estado estado) {
		Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
		
		if(estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			
			Estado estadoSalvo = cadastroEstado.salvar(estadoAtual.get());			
			return ResponseEntity.ok(estadoSalvo);			
		}
		
		return ResponseEntity.notFound().build();		
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.excluir(estadoId);			
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	
//	@PatchMapping("/{estadoId}")
//	public ResponseEntity<?> atualizarParcial(@PathVariable Long estadoId,
//				@RequestBody Map<String, Object> campos) {
//		Optional<Estado> estadoAutal = estadoRepository.findById(estadoId);
//		
//		if(estadoAutal == null) {
//			return ResponseEntity.notFound().build();  
//		}
//		
//		merge(campos, estadoAutal);
//		
//		
//		return atualizar(estadoId, estadoAutal);
//	}

}
