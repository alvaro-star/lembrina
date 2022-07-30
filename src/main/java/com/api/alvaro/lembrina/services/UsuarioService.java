package com.api.alvaro.lembrina.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.dtos.UsuarioDto;
import com.api.alvaro.lembrina.models.UsuarioModel;
import com.api.alvaro.lembrina.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	@Transactional
	public UsuarioModel save(UsuarioModel usuarioModel) {
		String encoder = this.passwordEncoder.encode(usuarioModel.getSenha());
		usuarioModel.setSenha(encoder);
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

	public UsuarioModel dtoToModel(UsuarioDto usuarioDto){
		var usuarioModel = new UsuarioModel();
		usuarioModel.setNome(usuarioDto.getNome());
		usuarioModel.setEmail(usuarioDto.getEmail());
		usuarioModel.setCampus(usuarioDto.getCampus());
		usuarioModel.setFuncao(usuarioDto.getFuncao());
		usuarioModel.setCpf(usuarioDto.getCpf());
		usuarioModel.setSenha(usuarioDto.getSenha());
		return usuarioModel;
	}

	public Boolean validarSenha(UsuarioModel usuarioModel){
		String senha = usuarioRepository.findById(usuarioModel.getId()).get().getSenha();
		boolean valido = passwordEncoder.matches(usuarioModel.getSenha(), senha);

		return valido;
	}
}
