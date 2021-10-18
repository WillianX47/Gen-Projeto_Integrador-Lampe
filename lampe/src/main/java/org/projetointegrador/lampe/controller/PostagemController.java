package org.projetointegrador.lampe.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.projetointegrador.lampe.model.PostagemModel;
import org.projetointegrador.lampe.repository.PostagemRepository;
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
@RequestMapping("/api/v1/postagem")
@Api(tags = "Controlador de postagem", description = "Utilitário de postagem")
@CrossOrigin("*")
public class PostagemController {

	private @Autowired PostagemRepository repositorio;

	@ApiOperation(value = "Procura todas as postagens cadastrados no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagens encontradas"),
			@ApiResponse(code = 204, message = "Não existem postagens no sistema") })
	@GetMapping
	public ResponseEntity<List<PostagemModel>> findAll() {
		List<PostagemModel> postagem = repositorio.findAll();
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
		return repositorio.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(204).build());

	}

	@ApiOperation(value = "Salva uma postagem")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem salva"),
			@ApiResponse(code = 400, message = "Erro no body") })
	@PostMapping("/salvar")
	public ResponseEntity<PostagemModel> inserir(@Valid @RequestBody PostagemModel postagem) {
		return ResponseEntity.status(200).body(repositorio.save(postagem));
	}

	@ApiOperation(value = "Atualiza uma postagem")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem atualizada"),
			@ApiResponse(code = 204, message = "Não existe essa postagem no sistema") })
	@PutMapping("/atualizar")
	public ResponseEntity<PostagemModel> alterar(@Valid @RequestBody PostagemModel postagem) {
		return ResponseEntity.ok(repositorio.save(postagem));
	}

	@ApiOperation(value = "Deleta uma postagem")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Postagem deletada"),
			@ApiResponse(code = 400, message = "Não existe essa postagem no sistema") })
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<PostagemModel> excluir(@Valid @PathVariable Long id) {
		Optional<PostagemModel> objetoOptional = repositorio.findById(id);
		if (objetoOptional.isPresent()) {
			repositorio.deleteById(id);
			return ResponseEntity.status(200).build();

		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
