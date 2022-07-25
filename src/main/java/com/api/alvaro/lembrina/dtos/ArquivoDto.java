package com.api.alvaro.lembrina.dtos;

public class ArquivoDto {

	private String nome;
	private String url;
	private String nomeLocal;

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
