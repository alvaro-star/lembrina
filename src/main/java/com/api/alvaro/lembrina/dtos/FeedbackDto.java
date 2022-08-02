package com.api.alvaro.lembrina.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FeedbackDto {

	@NotBlank(message = "Insira uma descricao")
	private String descricao;
	@NotNull(message = "Escolha um conteudo Valido")
	private Integer idConteudo;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getIdConteudo() {
		return idConteudo;
	}
	public void setIdConteudo(Integer idConteudo) {
		this.idConteudo = idConteudo;
	}
	
	
}
