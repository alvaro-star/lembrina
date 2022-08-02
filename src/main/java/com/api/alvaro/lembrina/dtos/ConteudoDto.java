package com.api.alvaro.lembrina.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ConteudoDto{

	@NotBlank(message = "Insira o nome do conteudo")
	private String nome;
	@NotBlank(message = "Insira a descricao do conteudo")
	private String descricao;
	
	@NotNull(message = "Insira um id valido")
	private Integer idMateria;
	
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
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	

}
