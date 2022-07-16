package com.api.alvaro.lembrina.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.models.UsuarioModel;
import com.api.alvaro.lembrina.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Transactional
	public UsuarioModel save(UsuarioModel usuarioModel) {
		return usuarioRepository.save(usuarioModel);
	}
	
	public List<UsuarioModel> findAll() {
		return usuarioRepository.findAll();
	}

	public Optional<UsuarioModel> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	@Transactional
	public void delete(UsuarioModel usuarioModel) {
		usuarioRepository.delete(usuarioModel);
	}
}
