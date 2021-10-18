package org.projetointegrador.lampe.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.projetointegrador.lampe.model.UsuarioLogin;
import org.projetointegrador.lampe.model.UsuarioModel;
import org.projetointegrador.lampe.repository.UsuarioRepository;
import org.projetointegrador.lampe.service.UsuarioService;
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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/v1/usuario")
@Api(tags = "Controlador de usuario", description = "Utilitário de usuario")
@CrossOrigin("*")
public class UsuarioController {

	private @Autowired UsuarioRepository repositorio;

	private @Autowired UsuarioService service;

	@ApiOperation(value = "Procura todos os usuários cadastrados no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuários encontrados"),
			@ApiResponse(code = 204, message = "Não existe usuários no sistema") })
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		List<UsuarioModel> objetoUsuario = repositorio.findAll();
		if (objetoUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.ok(objetoUsuario);

		}

	}

	@ApiOperation(value = "Executa o login de usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Login efetuado"),
			@ApiResponse(code = 401, message = "Usuario ou senha incorreto") })
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return service.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@ApiOperation(value = "Realiza o cadastro de usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastro efetuado"),
			@ApiResponse(code = 400, message = "Usuário já existe no sistema") })
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(user));
	}

	@ApiOperation(value = "Atualiza um usuário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Atualização efetuada"),
			@ApiResponse(code = 400, message = "Usuário inexistente no sistema") })
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> alterarUsuario(@Valid @RequestBody UsuarioModel alterarUsuario) {
		return ResponseEntity.status(201).body(repositorio.save(alterarUsuario));

	}

	@ApiOperation(value = "Deleta um usuario do sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuário deletado"),
			@ApiResponse(code = 400, message = "Usuário inexistente no sistema") })
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

	@ApiOperation(value = "Procura um usuario por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuario encontrado"),
			@ApiResponse(code = 204, message = "Usuario inexistente no sistema") })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> findAllById(@Valid @PathVariable Long id) {
		return repositorio.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());

	}

}
