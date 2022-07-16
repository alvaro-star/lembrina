package com.api.alvaro.lembrina.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.alvaro.lembrina.dtos.UsuarioDto;
import com.api.alvaro.lembrina.models.UsuarioModel;
import com.api.alvaro.lembrina.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Object> saveUser(@RequestBody @Valid UsuarioDto usuarioDto) {
		var usuarioModel = new UsuarioModel();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
	}

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneUsuario(@PathVariable(value = "id") Integer id) {
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if (!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());
	}


	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid UsuarioDto usuarioDto) {
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if (!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
		}
		var usuarioModel = new UsuarioModel();
		BeanUtils.copyProperties(usuarioDto, usuarioModel);
		usuarioModel.setId(usuarioModelOptional.get().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Integer id) {
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if (!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
		}else if(!usuarioModelOptional.get().getMaterias().isEmpty()) {
        	return ResponseEntity.status(HttpStatus.CONFLICT).body("O objeto esta vinculado a uma materia");
        }
		usuarioService.delete(usuarioModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body("Delecao realizada com sucessor");
	}
	//Metodos Customizados
	
	@GetMapping("/{id}/materias")
	public ResponseEntity<Object> getMateriasDoUsuario(@PathVariable(value = "id") Integer id) {
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(id);
		if (!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get().getMaterias());
	}
}
