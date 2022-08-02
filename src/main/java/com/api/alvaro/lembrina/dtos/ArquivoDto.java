package com.api.alvaro.lembrina.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ArquivoDto {

	@NotBlank(message = "Insira o nome corretamente")
	private String nome;
	@NotBlank(message = "Insira a url corretamente")
	private String url;
	@NotBlank(message = "Insira o nome local corretamente")
	private String nomeLocal;

	
	@NotNull(message = "Insira um conteudo Valido")
	private Integer idConteudo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdConteudo() {
		return idConteudo;
	}
	public void setIdConteudo(Integer idConteudo) {
		this.idConteudo = idConteudo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}
	
	
}
