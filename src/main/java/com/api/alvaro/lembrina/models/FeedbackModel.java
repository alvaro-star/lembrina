package com.api.alvaro.lembrina.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "tb_feedback")
public class FeedbackModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtb_feedback")
	private Integer id;
	@Column(nullable = false, length = 200)
	private String descricao;
	@Column(nullable = false)
	private LocalDateTime postado_em;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_idtb_conteudo")
	@JsonProperty(access = Access.WRITE_ONLY)
	private ConteudoModel conteudo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getPostado_em() {
		return postado_em;
	}
	public void setPostado_em(LocalDateTime postado_em) {
		this.postado_em = postado_em;
	}
	public ConteudoModel getConteudo() {
		return conteudo;
	}
	public void setConteudo(ConteudoModel conteudo) {
		this.conteudo = conteudo;
	}
	
	
}
