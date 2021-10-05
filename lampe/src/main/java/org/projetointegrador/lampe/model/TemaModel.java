package org.projetointegrador.lampe.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_tema")
public class TemaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, max = 100)
	private String primarioTema;
	
	@Size(min = 5, max = 100)
	private String secundarioTema;
	
	@Size(min = 5, max = 100)
	private String eventosTema;
	
	@OneToMany(mappedBy = "temaPostagem", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"temaPostagem"})
	private List<PostagemModel> postagens = new ArrayList<>();

	public List<PostagemModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<PostagemModel> postagens) {
		this.postagens = postagens;
	}

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
