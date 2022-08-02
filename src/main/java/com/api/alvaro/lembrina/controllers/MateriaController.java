package com.api.alvaro.lembrina.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.alvaro.lembrina.dtos.MateriaDto;
import com.api.alvaro.lembrina.helpers.Erros;
import com.api.alvaro.lembrina.models.MateriaModel;
import com.api.alvaro.lembrina.models.UsuarioModel;
import com.api.alvaro.lembrina.services.MateriaService;
import com.api.alvaro.lembrina.services.UsuarioService;

@RestController
@RequestMapping("/materias")
@CrossOrigin(origins = "*")
public class MateriaController extends Erros{

	@Autowired
	private MateriaService materiaService;
	@Autowired
	private UsuarioService usuarioService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
    public ResponseEntity<Object> criarMateria(@RequestBody @Valid MateriaDto materiaDto){
        var materiaModel = new MateriaModel();
        BeanUtils.copyProperties(materiaDto, materiaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.save(materiaModel));
    }
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')")
	@GetMapping
	public ResponseEntity<List<MateriaModel>> getAllMaterias(){
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") Integer id){
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(id);
		if(!materiaModelOptional.isPresent()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
        }
    	return ResponseEntity.status(HttpStatus.OK).body(materiaModelOptional.get());
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateMateria(@PathVariable(value = "id") Integer id, 
												@RequestBody @Valid MateriaDto materiaDto){
        Optional<MateriaModel> materiaModelOptional = materiaService.findById(id);
        if(!materiaModelOptional.isPresent()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
        }
        
        var materiaModel = new MateriaModel();
        BeanUtils.copyProperties(materiaDto, materiaModel);
        materiaModel.setId(materiaModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.save(materiaModel));
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Integer id) {
        Optional<MateriaModel> materiaModelOptional = materiaService.findById(id);
        if(!materiaModelOptional.isPresent()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
        }else if(!materiaModelOptional.get().getUsuarios().isEmpty()) {
        	return ResponseEntity.status(HttpStatus.CONFLICT).body("O objeto esta vinculado a um usuario");
        }
		materiaService.delete(materiaModelOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body("Delecao realizada com sucessor");
	}
	
	//Metodos Customizados
	
	@PutMapping("/{idMateria}/enrolarUsuario/{idUsuario}")
	public ResponseEntity<Object> relacionarMateriaUsuario(@PathVariable(value = "idMateria") Integer idMateria, @PathVariable(name = "idUsuario") Integer idUsuario){
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(idMateria);
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(idUsuario);
		if(!materiaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia not found");
		}else if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found");
		}
		
		materiaModelOptional.get().addUser(usuarioModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.save(materiaModelOptional.get()));
	}
	
	@PutMapping("/{idMateria}/desenrolarUsuario/{idUsuario}")
	public ResponseEntity<Object> desrelacionarMateriaUsuario(@PathVariable(value = "idMateria") Integer idMateria, @PathVariable(name = "idUsuario") Integer idUsuario){
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(idMateria);
		Optional<UsuarioModel> usuarioModelOptional = usuarioService.findById(idUsuario);
		if(!materiaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Materia not found");
		}else if(!usuarioModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found");
		}
		materiaModelOptional.get().removeUser(usuarioModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body(materiaService.save(materiaModelOptional.get()));
	}
	
	
	@GetMapping("/{id}/usuarios")
    public ResponseEntity<Object> getMateriaUsuarios(@PathVariable(value = "id") Integer id){
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(id);
		if(!materiaModelOptional.isPresent()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
        }
    	return ResponseEntity.status(HttpStatus.OK).body(materiaModelOptional.get().getUsuarios());
    }
	
	@GetMapping("/{id}/conteudos")
	public ResponseEntity<Object> getMateriaConteudo(@PathVariable(value = "id") Integer id){
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(id);
		if(!materiaModelOptional.isPresent()) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
        }
    	return ResponseEntity.status(HttpStatus.OK).body(materiaModelOptional.get().getConteudos());
    }
	
}
