package com.api.alvaro.lembrina.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "tb_conteudo")
public class ConteudoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtb_conteudo")
	private Integer id;
	@Column(nullable = false, length = 70)
	private String nome;
	@Column(nullable = false, length = 70)
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_idtb_materia")
	@JsonProperty(access = Access.WRITE_ONLY)
	private MateriaModel materia;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conteudo")
	private List<ArquivoModel> arquivos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conteudo")
	private List<FeedbackModel> feedbacks = new ArrayList<>();
	
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
	public MateriaModel getMateria() {
		return materia;
	}
	public void setMateria(MateriaModel materia) {
		this.materia = materia;
	}
	public List<ArquivoModel> getArquivos() {
		return arquivos;
	}
	public void setArquivos(List<ArquivoModel> arquivos) {
		this.arquivos = arquivos;
		for(ArquivoModel arquivoModel : arquivos) {
			arquivoModel.setConteudo(this);
		}
	}
	public List<FeedbackModel> getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(List<FeedbackModel> feedbacks) {
		this.feedbacks = feedbacks;
		for(FeedbackModel feedbackModel : feedbacks) {
			feedbackModel.setConteudo(this);
		}
	}
	
	
}
