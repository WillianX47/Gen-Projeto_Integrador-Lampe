package org.projetointegrador.lampe.repository;

import org.projetointegrador.lampe.model.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {
	
	}
