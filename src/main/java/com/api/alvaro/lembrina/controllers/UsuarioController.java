package com.api.alvaro.lembrina.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UsuarioDto usuarioDto){
        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDto, usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAllUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
	}
}
