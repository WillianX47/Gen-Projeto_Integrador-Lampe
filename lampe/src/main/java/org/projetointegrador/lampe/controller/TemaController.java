package org.projetointegrador.lampe.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.projetointegrador.lampe.model.TemaModel;
import org.projetointegrador.lampe.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de Tema
 * @author Eduardo
 * 
 *
 */
@RestController
@RequestMapping("/api/v1/tema")
@CrossOrigin("*")
public class TemaController {

	private @Autowired TemaRepository repositorio;

	/**
	 * Mostra uma lista de todos os temas
	 * 
	 */
	@GetMapping
	public ResponseEntity<List<TemaModel>> findAllTema() {
		List<TemaModel> objetoTema = repositorio.findAll();
		if (objetoTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoTema);
		}
	}
	/**
	 * Salva o tema requisitado pelo front end
	 * @param novoTema
	 * 
	 */
	@PostMapping("/salvar")
	public ResponseEntity<TemaModel> novoTema(@Valid @RequestBody TemaModel novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));

	}
	/**
	 * Altera o tema solicitado
	 * @param alterarTema
	 * 
	 */
	@PutMapping("/atualizar")
	public ResponseEntity<TemaModel> alterarTema(@Valid @RequestBody TemaModel alterarTema) {
		return ResponseEntity.status(200).body(repositorio.save(alterarTema));

	}
	/**
	 * Busca um tema por id
	 * @param id
	 * 
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TemaModel> findAllById(@Valid @PathVariable Long id) {
		return repositorio.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	/**
	 * Exclui determinado tema por id
	 * @param id
	 * 
	 */
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<TemaModel> excluirTema(@Valid @PathVariable Long id) {
		Optional<TemaModel> objetoOptional = repositorio.findById(id);
		if (objetoOptional.isPresent()) {
			repositorio.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();
		}

	}

}
