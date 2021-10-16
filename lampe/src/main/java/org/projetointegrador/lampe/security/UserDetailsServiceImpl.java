package org.projetointegrador.lampe.security;

import java.util.Optional;

import org.projetointegrador.lampe.model.UsuarioModel;
import org.projetointegrador.lampe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UsuarioModel> email = repositorio.findByEmailUsuario(username);
		email.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
		return email.map(UserDetailsImpl::new).get();
	}

}