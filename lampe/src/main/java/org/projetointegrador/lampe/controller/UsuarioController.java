package org.projetointegrador.lampe.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.projetointegrador.lampe.model.UsuarioModel;
import org.projetointegrador.lampe.repository.UsuarioRepository;
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

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioRepository repositorio;

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		List<UsuarioModel> objetoUsuario = repositorio.findAll();
		if (objetoUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.ok(objetoUsuario);

		}

	}

	@PostMapping("/salvar")
	public ResponseEntity<UsuarioModel> novoUsuario(@Valid @RequestBody UsuarioModel novoUsuario) {
		return ResponseEntity.status(201).body(repositorio.save(novoUsuario));

	}

	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> alterarUsuario(@Valid @RequestBody UsuarioModel alterarUsuario) {
		return ResponseEntity.status(201).body(repositorio.save(alterarUsuario));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UsuarioModel> deleteUsuario(@Valid @PathVariable Long id) {
		Optional<UsuarioModel> objetoOptional = repositorio.findById(id);
		if (objetoOptional.isPresent()) {
			repositorio.deleteById(id);
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(400).build();

		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> findAllById(@Valid @PathVariable Long id) {
		return repositorio.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
		
				
	}

}
