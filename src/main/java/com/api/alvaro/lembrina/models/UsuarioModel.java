package com.api.alvaro.lembrina.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_usuario")
public class UsuarioModel implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtb_usuario")
	private Integer id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 70, unique = true)
	private String email;
	@Column(nullable = false, length = 70)
	private String campus;
	@Column(nullable = false, length = 70)
	private String funcao;
	@Column(nullable = false, unique = true, length = 12)
	private String cpf;
	@Column(nullable = false, length = 50)
	private String senha;
	
	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "tb_usuario_materia", 
			joinColumns = @JoinColumn(name = "idtb_usuario", referencedColumnName = "idtb_usuario"), 
			inverseJoinColumns = @JoinColumn(name = "idtb_role", referencedColumnName = "idtb_role"))
	List<RoleModel> roles;
	
	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "tb_usuario_materia", 
				joinColumns = @JoinColumn(name = "idtb_usuario", referencedColumnName = "idtb_usuario"), 
				inverseJoinColumns = @JoinColumn(name = "idtb_materia", referencedColumnName = "idtb_materia"))
	private List<MateriaModel> materias = new ArrayList<>();
	
	
	public UsuarioModel() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public List<MateriaModel> getMaterias() {
		return materias;
	}
	public void setMaterias(List<MateriaModel> materias) {
		this.materias = materias;
	}
	
	
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}
	
	@Override
	public String getUsername() {
		return this.email;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
