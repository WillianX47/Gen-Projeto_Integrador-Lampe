package org.projetointegrador.lampe.repository;

import org.projetointegrador.lampe.model.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long> {

}
