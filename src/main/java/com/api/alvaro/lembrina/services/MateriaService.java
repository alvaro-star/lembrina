package com.api.alvaro.lembrina.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.models.MateriaModel;
import com.api.alvaro.lembrina.repositories.MateriaRepository;

@Service
public class MateriaService {
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Transactional
	public MateriaModel save(MateriaModel materiaModel) {
		return materiaRepository.save(materiaModel);
	}
	
	public List<MateriaModel> findAll() {
		return materiaRepository.findAll();
	}

	public Optional<MateriaModel> findById(Integer id) {
		return materiaRepository.findById(id);
	}

	@Transactional
	public void delete(MateriaModel materiaModel) {
		materiaRepository.delete(materiaModel);
	}
}
