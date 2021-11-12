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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Cria uma tabela no banco de dados com o nome "tb_usuario"

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel {

	// Gera o id automaticamente
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	// Cria o atributo do nome do usuario
	private @NotBlank String nomeUsuario;

	// Cria o atributo do email do usuario
	private @NotBlank String emailUsuario;

	// Cria o atributo da senha do usuario
	private @NotBlank String senhaUsuario;// Cria um atributo do tipo foto

	// Cria um atributo do tipo foto
	private String foto;

	// Link tabela OneToMany para a tabela de postagem
	@OneToMany(mappedBy = "usuarioPostagem", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "usuarioPostagem" })
	private List<PostagemModel> postagens = new ArrayList<>();

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public List<PostagemModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<PostagemModel> postagens) {
		this.postagens = postagens;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}
	
}
