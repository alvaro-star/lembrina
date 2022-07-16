package com.api.alvaro.lembrina.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.models.ConteudoModel;
import com.api.alvaro.lembrina.repositories.ConteudoRepository;

@Service
public class ConteudoService {
	
	@Autowired
	ConteudoRepository conteudoRepository;
	
	@Transactional
	public ConteudoModel save(ConteudoModel conteudoModel) {
		return conteudoRepository.save(conteudoModel);
	}
	
	public Page<ConteudoModel> findAll(Pageable pageable) {
		return conteudoRepository.findAll(pageable);
	}

	public Optional<ConteudoModel> findById(Integer id) {
		return conteudoRepository.findById(id);
	}

	@Transactional
	public void delete(ConteudoModel conteudoModel) {
		conteudoRepository.delete(conteudoModel);
	}
}
