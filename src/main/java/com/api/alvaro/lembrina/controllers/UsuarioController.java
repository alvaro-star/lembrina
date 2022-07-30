package com.api.alvaro.lembrina.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.alvaro.lembrina.dtos.UsuarioDto;
import com.api.alvaro.lembrina.models.UsuarioModel;
import com.api.alvaro.lembrina.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
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

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid UsuarioDto usuarioDto) {
		var usuarioModel = usuarioService.dtoToModel(usuarioDto);
		Boolean verificacao = usuarioService.validarSenha(usuarioModel);

		if(!verificacao){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario nao foi autorizado");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException execao){
		Map<String, String> erros = new HashMap<>();

		execao.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			erros.put(fieldName, errorMessage);
		});

		return erros;
	}
}
