package com.api.alvaro.lembrina.dtos;

import javax.validation.constraints.NotBlank;

public class UsuarioDto {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String email;
	@NotBlank
	private String campus;
	@NotBlank
	private String funcao;
	@NotBlank
	private String cpf;
	@NotBlank
	private String senha;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
