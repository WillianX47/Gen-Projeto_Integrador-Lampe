package org.projetointegrador.lampe.configuration;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	/**
	 * Define a package onde estão as classes do tipo @RestController, para que o
	 * Swagger mapeie todas as classes e seus respectivos endpoints para montar a
	 * documentação do projeto.
	 * 
	 * @author Will
	 * @return Docket
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.projetointegrador.lampe.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false);
	}

	/**
	 * (.title)Define o titulo da sua aplicação que será exibida na documentação.
	 * (.description)Cria uma descrição para a sua aplicação. (.version)Define a
	 * versão da sua aplicação (.license)Define o tipo de licença da sua aplicação.
	 * (.licenseUrl)Informa o link de acesso da licença da sua aplicação (geralmente
	 * se aplica a licença no Github). Define os dados para contato com o
	 * desenvolvedor inseridos no método contact().
	 * 
	 * @author Will
	 * @return ApiInfo
	 */
	public static ApiInfo metadata() {
		return new ApiInfoBuilder().title("API - Lampe").description("Projeto API Spring - Lampe").version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/WillianX47/Gen-Projeto_Integrador-Lampe.git\r\n" + "")
				.contact(contact()).build();
	}

	/**
	 * Define os dados do Desenvolvedor (Nome, Website e o E-mail).
	 * 
	 * @return Contact
	 */
	private static Contact contact() {
		return new Contact("Lampe", "https://github.com/WillianX47/Gen-Projeto_Integrador-Lampe.git\\r\\n", "");
	}

}