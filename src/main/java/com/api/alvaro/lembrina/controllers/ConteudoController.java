package com.api.alvaro.lembrina.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

import com.api.alvaro.lembrina.dtos.ConteudoDto;
import com.api.alvaro.lembrina.models.ConteudoModel;
import com.api.alvaro.lembrina.models.MateriaModel;
import com.api.alvaro.lembrina.services.ConteudoService;
import com.api.alvaro.lembrina.services.MateriaService;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

	@Autowired
	private ConteudoService conteudoService;
	@Autowired
	private MateriaService materiaService;
	
	@GetMapping
	public ResponseEntity<Page<ConteudoModel>> getAllCars(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(conteudoService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") Integer id){
		Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(id);
		if(!conteudoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Not Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(conteudoModelOptional.get());
	}
	
	@PostMapping
    public ResponseEntity<Object> saveCarro(@RequestBody @Valid ConteudoDto conteudoDto){
		var conteudoModel = conteudoService.dtoToModel(conteudoDto);
		
		Optional<MateriaModel> materiaModelOptional = materiaService.findById(conteudoDto.getIdMateria());
		if(!materiaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found the Object Materia");
		}
		conteudoModel.setMateria(materiaModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(conteudoService.save(conteudoModel));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") Integer id, 
    												@RequestBody @Valid ConteudoDto conteudoDto){
    	Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(id);
    	if(!conteudoModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found the object Conteudo");
    	}
    	
    	Optional<MateriaModel> materiaModelOptional = materiaService.findById(conteudoDto.getIdMateria());
		if(!materiaModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found the Object Materia");
		}
		
    	var conteudoModel = conteudoService.dtoToModel(conteudoDto);
    	conteudoModel.setId(conteudoModelOptional.get().getId());
    	conteudoModel.setMateria(materiaModelOptional.get());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(conteudoService.save(conteudoModel));
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") Integer id){
    	Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(id);
    	if(!conteudoModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found the object Conteudo");
    	}else if(!conteudoModelOptional.get().getArquivos().isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O conteudo contem pelo menos um arquivo");
    	}else if(!conteudoModelOptional.get().getFeedbacks().isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O conteudo contem pelo menos um Feedback");
    	}
    	conteudoService.delete(conteudoModelOptional.get());
    	return ResponseEntity.status(HttpStatus.OK).body("Conteudo deleted succesfully");
    }
}
