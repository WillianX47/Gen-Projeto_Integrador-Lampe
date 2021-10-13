package org.projetointegrador.lampe.model;

/**
 * Class espelho de Usuario
 */
public class UsuarioLogin {
	
	/**
	 * Nome do Usuario
	 */
	private String nomeUsuario;
	
	/**
	 * Email do Usuario
	 */
	private String emailUsuario;

	/**
	 * Senha do Usuario
	 */
	private String senhaUsuario;

	/**
	 * Token do Usuario
	 */
	private String token;

	/**
	 * Id do Usuario
	 */
	private Long id;

	public String getNomeUsuario() {
		return nomeUsuario;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}