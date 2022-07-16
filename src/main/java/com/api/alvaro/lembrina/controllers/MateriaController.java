package com.api.alvaro.lembrina.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.alvaro.lembrina.dtos.MateriaDto;
import com.api.alvaro.lembrina.models.MateriaModel;
import com.api.alvaro.lembrina.models.UsuarioModel;
import com.api.alvaro.lembrina.services.MateriaService;
import com.api.alvaro.lembrina.services.UsuarioService;

@RestController
@RequestMapping("/materias")
public class MateriaController {

	@Autowired
	private MateriaService materiaService;
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
    public ResponseEntity<Object> criarMateria(@RequestBody @Valid MateriaDto materiaDto){
        var materiaModel = new MateriaModel();
        BeanUtils.copyProperties(materiaDto, materiaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.save(materiaModel));
    }
	
	@GetMapping
	public ResponseEntity<List<MateriaModel>> getAllMaterias(){
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.findAll());
	}
	
	@PutMapping("/{idMateria}/enrolarUsuario/{idUsuario}")
	public ResponseEntity<Object> relacionarMateriaUsuario(
		@PathVariable(name = "idMateria") Integer idMateria,
		@PathVariable(name = "idUsuario") Integer idUsuario 
	){
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(idMateria);
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(idUsuario);
		materiaModelOptional.get().addUser(usuarioModelOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.save(materiaModelOptional.get()));
		
	}
}
