package com.api.alvaro.lembrina.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name= "tb_role")
public class RoleModel implements GrantedAuthority, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtb_role")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
	
	@ManyToMany
	@JoinTable(name = "tb_usuario_materia", 
			joinColumns = @JoinColumn(name = "idtb_role", referencedColumnName = "idtb_role"), 
			inverseJoinColumns = @JoinColumn(name = "idtb_usuario", referencedColumnName = "idtb_usuario"))
	List<UsuarioModel> usuarios;
	
	@Override
	public String getAuthority() {
		return this.roleName.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
	
}
