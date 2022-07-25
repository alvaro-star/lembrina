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
@Table(name = "tb_arquivo")
public class ArquivoModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtb_arquivo")
	private Integer id;
	@Column(nullable = false, length = 70)
	private String nome;
	@Column(nullable = false)
	private String nomeLocal;
	@Column(nullable = false)
	private LocalDateTime criado_em;
	@Column(nullable = false, length = 120)
	private String url;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDateTime getCriado_em() {
		return criado_em;
	}
	public void setCriado_em(LocalDateTime criado_em) {
		this.criado_em = criado_em;
	}

	
	public ConteudoModel getConteudo() {
		return conteudo;
	}
	public void setConteudo(ConteudoModel conteudo) {
		this.conteudo = conteudo;
	}

	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
