package org.projetointegrador.lampe.controller;

import java.util.List;

import javax.validation.Valid;

import org.projetointegrador.lampe.model.TemaModel;
import org.projetointegrador.lampe.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/tema")
@Api(tags = "Controlador de tema", description = "Utilitário de tema")
@CrossOrigin("*")
public class TemaController {

	private @Autowired TemaRepository repository;

	@ApiOperation(value = "Procura todas os temas cadastrados no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Temas encontrados"),
			@ApiResponse(code = 204, message = "Não existem temas no sistema") })
	@GetMapping
	public ResponseEntity<List<TemaModel>> findAllTema() {
		List<TemaModel> objetoTema = repository.findAll();
		if (objetoTema.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoTema);
		}
	}

	@ApiOperation(value = "Salvar um tema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tema salvar"),})
	@PostMapping("/salvar")
	public ResponseEntity<TemaModel> novoTema(@Valid @RequestBody TemaModel novoTema) {
		return ResponseEntity.status(201).body(repository.save(novoTema));
	}

	@ApiOperation(value = "Atualizar um tema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Tema atualizado"),})
	@PutMapping("/atualizar")
	public ResponseEntity<TemaModel> alterarTema(@Valid @RequestBody TemaModel alterarTema) {
		return ResponseEntity.status(200).body(repository.save(alterarTema));

	}

	@ApiOperation(value = "Procura um tema por Id")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tema encontrado"),
            @ApiResponse(code = 404, message = "Tema inexistente no sistema") })
    @GetMapping("/pesquisar")
    public ResponseEntity<TemaModel> findAllById(@Valid @RequestParam(defaultValue = "") Long id) {
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema inexistente");
        });
    }

	@ApiOperation(value = "Excluir um tema")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tema deletada"),
            @ApiResponse(code = 404, message = "Não existe esse tema no sistema") })
    @DeleteMapping("/excluir")
    public ResponseEntity<Object> excluirTema(@Valid @RequestParam(defaultValue = "") Long id) {
        return repository.findById(id).map(resp -> {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tema inexistente");
        });

    }

}