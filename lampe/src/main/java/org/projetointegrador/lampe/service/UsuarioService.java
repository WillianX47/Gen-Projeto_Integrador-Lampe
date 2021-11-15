package org.projetointegrador.lampe.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.projetointegrador.lampe.model.UsuarioLogin;
import org.projetointegrador.lampe.model.UsuarioModel;
import org.projetointegrador.lampe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repository;

	public ResponseEntity<UsuarioModel> getUsuarioById(Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	public static String encriptadorSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	public Optional<Object> cadastrarUsuario(UsuarioModel usuario) {
		return repository.findByEmailUsuario(usuario.getEmailUsuario()).map(resp -> {
			return Optional.empty();
		}).orElseGet(() -> {
			usuario.setSenhaUsuario(encriptadorSenha(usuario.getSenhaUsuario()));
			return Optional.ofNullable(repository.save(usuario));
		});
	}

	public ResponseEntity<UsuarioModel> atualizarUsuario(UsuarioModel upUsuario) {
		return repository.findById(upUsuario.getId()).map(resp -> {
			resp.setNomeUsuario(upUsuario.getNomeUsuario());
			resp.setFoto(upUsuario.getFoto());
			resp.setEmailUsuario(upUsuario.getEmailUsuario());
			resp.setSenhaUsuario(encriptadorSenha(upUsuario.getSenhaUsuario()));
			return ResponseEntity.ok(repository.save(resp));
		}).orElseGet(() -> {
			return ResponseEntity.badRequest().build();
		});
	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UsuarioModel> usuario = repository.findByEmailUsuario(user.get().getEmailUsuario());
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {
				String auth = user.get().getEmailUsuario() + ":" + user.get().getSenhaUsuario();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNomeUsuario(usuario.get().getNomeUsuario());
				user.get().setEmailUsuario(usuario.get().getEmailUsuario());
				user.get().setSenhaUsuario(usuario.get().getSenhaUsuario());
				return user;
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
			}
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email incorreto");
	}

}
