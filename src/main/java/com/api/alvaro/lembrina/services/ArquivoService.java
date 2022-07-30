package com.api.alvaro.lembrina.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.api.alvaro.lembrina.dtos.ArquivoDto;
import com.api.alvaro.lembrina.helpers.Response;
import com.api.alvaro.lembrina.models.ArquivoModel;
import com.api.alvaro.lembrina.repositories.ArquivoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArquivoService {

	@Autowired
	ArquivoRepository arquivoRepository;
	
	@Transactional
	public ArquivoModel save(ArquivoModel arquivoModel) {
		return arquivoRepository.save(arquivoModel);
	}
	
	public Page<ArquivoModel> findAll(Pageable pageable) {
		return arquivoRepository.findAll(pageable);
	}

	public Optional<ArquivoModel> findById(Integer id) {
		return arquivoRepository.findById(id);
	}

	@Transactional
	public void delete(ArquivoModel arquivoModel) {
		arquivoRepository.delete(arquivoModel);
	}
	
	public ArquivoModel dtoToModel(ArquivoDto arquivoDto) {
		var arquivoModel = new ArquivoModel();
		arquivoModel.setNomeLocal(arquivoDto.getNomeLocal());
		arquivoModel.setUrl(arquivoDto.getUrl());
		arquivoModel.setNome(arquivoDto.getNome());
		return arquivoModel;
	}
	
	public Response validarCampos(){
		var resposta = new Response("");
		resposta.getErros().put("nome", "");
		resposta.getErros().put("url", "");
		resposta.getErros().put("nomeLocal", "");
		

		return resposta;
	}
}
