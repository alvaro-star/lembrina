package com.api.alvaro.lembrina.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDto {
	
	@NotBlank(message = "O campo nome é obrigatorio")
	@Size(min = 5, message = "O numero de caracterews é de 5 caracteres")
	private String nome;
	@Email(message = "Insira um email valido")
	@NotBlank(message = "O campo email é obrigatorio")
	private String email;
	@NotBlank(message = "O campo campus é obrigatorio")
	private String campus;
	@NotBlank(message = "O campo funcao é obrigatorio")
	private String funcao;
	@NotBlank(message = "O campo cpf é obrigatorio")
	private String cpf;
	@NotBlank(message = "O campo senha é obrigatorio")
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
