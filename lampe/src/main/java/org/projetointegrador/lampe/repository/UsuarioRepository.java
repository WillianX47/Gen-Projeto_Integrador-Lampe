package org.projetointegrador.lampe.repository;

import java.util.Optional;

import org.projetointegrador.lampe.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	public Optional<UsuarioModel> findByEmailUsuario(String usuario);
}
