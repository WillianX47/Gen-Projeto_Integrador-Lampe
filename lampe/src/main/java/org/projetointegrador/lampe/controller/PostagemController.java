package org.projetointegrador.lampe.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.projetointegrador.lampe.model.PostagemModel;
import org.projetointegrador.lampe.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1/postagem")
@Api(tags = "Controlador de postagem", description = "Utilitário de postagem")
@CrossOrigin("*")
public class PostagemController {

	private @Autowired PostagemRepository repository;

	@ApiOperation(value = "Procura todas as postagens cadastrados no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagens encontradas"),
			@ApiResponse(code = 204, message = "Não existem postagens no sistema") })
	@GetMapping
	public ResponseEntity<List<PostagemModel>> findAll() {
		List<PostagemModel> postagem = repository.findAll();
		if (postagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.ok(postagem);
		}
	}

	@ApiOperation(value = "Procura uma postagem por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem encontrada"),
			@ApiResponse(code = 204, message = "Postagem inexistente no sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<PostagemModel> findAllById(@Valid @PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(204).build());

	}

	@ApiOperation(value = "Salva uma postagem")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem salva"),})
	@PostMapping("/salvar")
	public ResponseEntity<PostagemModel> inserir(@Valid @RequestBody PostagemModel postagem) {
		return ResponseEntity.status(200).body(repository.save(postagem));
	}

	@ApiOperation(value = "Atualiza uma postagem")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem atualizada"),
			@ApiResponse(code = 204, message = "Não existe essa postagem no sistema") })
	@PutMapping("/atualizar")
	public ResponseEntity<PostagemModel> alterar(@Valid @RequestParam(defaultValue = "") Long id,
			@RequestBody PostagemModel postagem) {
		return repository.findById(id).map(resp -> {
			resp.setTextoPostagem(postagem.getTextoPostagem());
			resp.setAnexoPostagem(postagem.getAnexoPostagem());
			resp.setPrivacidade(postagem.getPrivacidade());
			resp.setLocalizacaoPostagem(postagem.getLocalizacaoPostagem());
			resp.setTemaPostagem(postagem.getTemaPostagem());
			resp.setUsuarioPostagem(resp.getUsuarioPostagem());
			return ResponseEntity.ok(repository.save(resp));
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe uma postagem com esse id");
		});
	}

	@ApiOperation(value = "Deleta uma postagem")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem deletada"),
			@ApiResponse(code = 404, message = "Não existe essa postagem no sistema") })
	@DeleteMapping("/excluir")
	public ResponseEntity<Object> excluir(@Valid @RequestParam(defaultValue = "") Long id) {
		return repository.findById(id).map(resp -> {
			repository.deleteById(id);
			return ResponseEntity.status(200).build();
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe uma postagem com esse id");
		});
	}

}
