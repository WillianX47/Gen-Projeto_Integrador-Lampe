package org.projetointegrador.lampe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_tema")
public class TemaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String primarioTema;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String secundarioTema;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String eventosTema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimarioTema() {
		return primarioTema;
	}

	public void setPrimarioTema(String primarioTema) {
		this.primarioTema = primarioTema;
	}

	public String getSecundarioTema() {
		return secundarioTema;
	}

	public void setSecundarioTema(String secundarioTema) {
		this.secundarioTema = secundarioTema;
	}

	public String getEventosTema() {
		return eventosTema;
	}

	public void setEventosTema(String eventosTema) {
		this.eventosTema = eventosTema;
	}
}
