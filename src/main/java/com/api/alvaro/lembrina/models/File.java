package com.api.alvaro.lembrina.models;

public class File {
	private String nome;
	private String url;
	
	public File(String nome, String url) {
		super();
		this.nome = nome;
		this.url = url;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
