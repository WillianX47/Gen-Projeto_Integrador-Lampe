package org.projetointegrador.lampe.controller;

import java.util.Optional;

import org.projetointegrador.lampe.model.UsuarioLogin;
import org.projetointegrador.lampe.model.UsuarioModel;
import org.projetointegrador.lampe.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	private @Autowired UsuarioService service;

	@ApiOperation(value = "Encontra um usuario por id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuario encontrado"),
			@ApiResponse(code = 404, message = "Usuario nao encontrado") })
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable Long id) {
		return service.getUsuarioById(id);
	}

	@ApiOperation(value = "Executa o login de usuario")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Login efetuado"),
			@ApiResponse(code = 400, message = "Usuario ou senha incorreto") })
	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user) {
		return service.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@ApiOperation(value = "Realiza o cadastro de usuario")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastro efetuado"),
			@ApiResponse(code = 400, message = "Usuário já existe no sistema") })
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> Post(@RequestBody UsuarioModel user) {
		return service.cadastrarUsuario(user).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(400).build());
	}

	@ApiOperation(value = "Atualiza um usuário no sistema usando seu ID.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Usuário atualizado."),
			@ApiResponse(code = 400, message = "Usuário inexistente.") })
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel userUpdate) {
		return service.atualizarUsuario(userUpdate);
	}

}
