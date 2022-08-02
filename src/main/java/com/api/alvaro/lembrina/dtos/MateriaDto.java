package com.api.alvaro.lembrina.dtos;

import javax.validation.constraints.NotBlank;

public class MateriaDto {

	@NotBlank(message = "Insira um nome")
	private String nome;
	@NotBlank(message = "Insira uma descricao")
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
