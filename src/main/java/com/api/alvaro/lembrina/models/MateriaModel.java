package com.api.alvaro.lembrina.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_materia")
public class MateriaModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtb_materia")
	private Integer id;
	@Column(nullable = false, length = 70)
	private String nome;
	@Column(nullable = false, length = 70)
	private String descricao;
	
	
	@ManyToMany
	@JoinTable(name = "tb_usuario_materia",
				joinColumns = @JoinColumn(name = "idtb_materia", referencedColumnName = "idtb_materia"), 
				inverseJoinColumns = @JoinColumn(name = "idtb_usuario", referencedColumnName = "idtb_usuario"))
	private List<UsuarioModel> usuarios = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "materia")
	private List<ConteudoModel> conteudos = new ArrayList<>();
	
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<ConteudoModel> getConteudos() {
		return conteudos;
	}
	public void setConteudos(List<ConteudoModel> conteudos) {
		this.conteudos = conteudos;
		for(ConteudoModel conteudoModel :conteudos) {
			conteudoModel.setMateria(this);
		}
	}
	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void addUser(UsuarioModel usuarioModel) {
		this.usuarios.add(usuarioModel);
	}
	
	public void removeUser(UsuarioModel usuarioModel) {
		this.usuarios.remove(usuarioModel);
	}
	
	
	
}
