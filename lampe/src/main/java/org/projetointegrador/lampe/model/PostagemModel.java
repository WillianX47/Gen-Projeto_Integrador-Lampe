package org.projetointegrador.lampe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagem")
@CrossOrigin("*")
public class PostagemModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	private @NotBlank String textoPostagem;

	private String anexoPostagem;

	private @NotBlank String privacidade;

	private String localizacaoPostagem;
	
	@ManyToOne
	@JoinColumn(name = "tema_id")
	@JsonIgnoreProperties({"postagens"})
	private @NotBlank TemaModel temaPostagem;
	
	private @NotBlank UsuarioModel usuarioPostagem;
	
	public TemaModel getTemaPostagem() {
		return temaPostagem;
	}
	
	public void setTemaPostagem(TemaModel temaPostagem) {
		this.temaPostagem = temaPostagem;
	}

	public UsuarioModel getUsuarioPostagem() {
		return usuarioPostagem;
	}

	public void setUsuarioPostagem(UsuarioModel usuarioPostagem) {
		this.usuarioPostagem = usuarioPostagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextoPostagem() {
		return textoPostagem;
	}

	public void setTextoPostagem(String textoPostagem) {
		this.textoPostagem = textoPostagem;
	}

	public String getAnexoPostagem() {
		return anexoPostagem;
	}

	public void setAnexoPostagem(String anexoPostagem) {
		this.anexoPostagem = anexoPostagem;
	}

	public String getPrivacidade() {
		return privacidade;
	}

	public void setPrivacidade(String privacidade) {
		this.privacidade = privacidade;
	}

	public String getLocalizacaoPostagem() {
		return localizacaoPostagem;
	}

	public void setLocalizacaoPostagem(String localizacaoPostagem) {
		this.localizacaoPostagem = localizacaoPostagem;
	}

}