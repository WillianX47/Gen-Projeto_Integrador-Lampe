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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/tema")
@Api(tags = "Controlador de tema", description = "Utilitário de tema")
@CrossOrigin("*")
public class TemaController {

	private @Autowired TemaRepository repositorio;

	@ApiOperation(value = "Procura todas os temas cadastrados no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Temas encontrados"),
			@ApiResponse(code = 204, message = "Não existem temas no sistema") })
	@GetMapping
	public ResponseEntity<List<TemaModel>> findAllTema() {
		List<TemaModel> objetoTema = repositorio.findAll();
		if (objetoTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoTema);
		}
	}

	@ApiOperation(value = "Salvar um tema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tema salvar"),
			@ApiResponse(code = 400, message = "Erro no body") })
	@PostMapping("/salvar")
	public ResponseEntity<TemaModel> novoTema(@Valid @RequestBody TemaModel novoTema) {
		return ResponseEntity.status(201).body(repositorio.save(novoTema));

	}

	@ApiOperation(value = "Atualizar um tema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tema atualizado"),
			@ApiResponse(code = 204, message = "Não existe esse tema no sistema") })
	@PutMapping("/atualizar")
	public ResponseEntity<TemaModel> alterarTema(@Valid @RequestBody TemaModel alterarTema) {
		return ResponseEntity.status(200).body(repositorio.save(alterarTema));

	}

	@ApiOperation(value = "Procura um tema por Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tema encontrado"),
			@ApiResponse(code = 204, message = "Tema inexistente no sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<TemaModel> findAllById(@Valid @PathVariable Long id) {
		return repositorio.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}

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
