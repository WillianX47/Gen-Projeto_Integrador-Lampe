/*
	Criação da base de dados
	Usando base de dados criada
*/
	
CREATE DATABASE db_lampe
USE db_lampe
	
/*
	Criação da tabela usuari
*/
	
CREATE TABLE tb_usuario(
	id_usuario BIGINT AUTO_INCREMENT NOT NULL,
	nome_usuario VARCHAR(30) NOT NULL,
	email_usuario VARCHAR(255) NOT NULL,
	senha_usuario VARCHAR(255) NOT NULL,
	PRIMARY KEY (id_usuario)
);

/*
	Insert dados em usuario:
*/
	INSERT INTO tb_usuario(nome_usuario, email_usuario, senha_usuario) VALUES ("Macauly", "Macauly@lampe.com", "1234");

/*
	Criação da tabela postagem
*/

CREATE TABLE tb_postagem(
	id_postagem BIGINT AUTO_INCREMENT NOT NULL,
	texto_postagem VARCHAR(255)NOT NULL,
	anexo_postagem VARCHAR(255),
	privacidade_postagem VARCHAR(255) NOT NULL,
	localizacao_postagem VARCHAR(255),
	usuario_id BIGINT DEFAULT NULL,
	tema_id BIGINT DEFAULT NULL,
	PRIMARY KEY (id_postagem),
	FOREIGN KEY (usuario_id) REFERENCES tb_usuario (id_usuario),
	FOREIGN KEY (tema_id) REFERENCES tb_tema (id_tema)
);

/*
	Insert into postagem
*/
	INSERT INTO tb_postagem(texto_postagem, anexo_postagem, privacidade_postagem, localizacao_postagem, usuario_id, tema_id) 
	VALUES ("Primeira postagem", NULL, "Publico", NULL, 1, 1);
/*
	Criação da tabela tema
*/

CREATE TABLE tb_tema(
	id_tema BIGINT AUTO_INCREMENT NOT NULL,
	primario_tema VARCHAR(30) NOT NULL,
	secundario_tema VARCHAR(30),
	eventos_tema VARCHAR(30),
	PRIMARY KEY (id_tema)
);

/*
	Insert into tb_tema
*/

INSERT INTO tb_tema(primario_tema, secundario_tema, eventos_tema) VALUES ("Principal", NULL, NULL);

/*
	Mostrar todos os dados da tabela:
*/

SELECT texto_postagem AS Mensagem, anexo_postagem AS Anexo, privacidade_postagem AS Privacidade, localizacao_postagem 
AS Localizacao, tb_usuario.nome_usuario AS Nome, tb_usuario.email_usuario AS Email, tb_usuario.senha_usuario AS Senha,
tb_tema.primario_tema AS Tema
FROM tb_postagem
INNER JOIN tb_usuario ON tb_postagem.usuario_id = tb_usuario.id_usuario
INNER JOIN tb_tema ON tb_postagem.tema_id = tb_tema.id_tema;