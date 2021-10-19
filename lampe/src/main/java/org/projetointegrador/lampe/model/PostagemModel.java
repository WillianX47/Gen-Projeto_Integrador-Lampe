package org.projetointegrador.lampe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Cria uma tabela no banco de dados com o nome "tb_postagem"
 * 
 */
@Entity
@Table(name = "tb_postagem")
public class PostagemModel {

	// Gera o id automaticamente
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	// Gera o atributo que recebe o texto da postagem
	private @NotBlank String textoPostagem;

	// Gera o atributo anexo postagem
	private String anexoPostagem;

	// Gera o atributo que define a privacidade da postagem
	private @NotBlank String privacidade;
	
	// Gera o atributo que recebe a localizacao da postagem
	private String localizacaoPostagem;

	// Link tabela ManyToOne para a tabela de tema
	@ManyToOne
	@JoinColumn(name = "tema_id")
	@JsonIgnoreProperties({ "postagens" })
	private TemaModel temaPostagem;

	// Link tabela ManyToOne para a tabela de usuario
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties({ "postagens" })
	private UsuarioModel usuarioPostagem;

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