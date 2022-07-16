package com.api.alvaro.lembrina.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.models.ArquivoModel;
import com.api.alvaro.lembrina.repositories.ArquivoRepository;

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
	
}
