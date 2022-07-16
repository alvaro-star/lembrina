package com.api.alvaro.lembrina.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
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

import com.api.alvaro.lembrina.dtos.ArquivoDto;
import com.api.alvaro.lembrina.models.ArquivoModel;
import com.api.alvaro.lembrina.models.ConteudoModel;
import com.api.alvaro.lembrina.services.ArquivoService;
import com.api.alvaro.lembrina.services.ConteudoService;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

	@Autowired
	private ArquivoService arquivoService;
	@Autowired
	private ConteudoService conteudoService;
	
	@GetMapping
	public ResponseEntity<Page<ArquivoModel>> getAllArquivos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(arquivoService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneArquivo(@PathVariable(value = "id") Integer id){
		Optional<ArquivoModel> arquivoModelOptional = arquivoService.findById(id);
		if(!arquivoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Not Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(arquivoModelOptional.get());
	}
	
	@PostMapping
    public ResponseEntity<Object> saveArquivo(@RequestBody @Valid ArquivoDto arquivoDto){
		var arquivoModel = arquivoService.dtoToModel(arquivoDto);
		
		Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(arquivoDto.getIdConteudo());
		if(!conteudoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found the Object Conteudo");
		}
		arquivoModel.setConteudo(conteudoModelOptional.get());
		arquivoModel.setCriado_em(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(arquivoService.save(arquivoModel));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArquivo(@PathVariable(value = "id") Integer id, 
    												@RequestBody @Valid ArquivoDto arquivoDto){
    	Optional<ArquivoModel> arquivoModelOptional = arquivoService.findById(id);
    	if(!arquivoModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found the object Arquivo");
    	}
    	
    	Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(arquivoDto.getIdConteudo());
		if(!conteudoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found the Object Conteudo");
		}
		
    	var arquivoModel = arquivoService.dtoToModel(arquivoDto);
    	arquivoModel.setId(arquivoModelOptional.get().getId());
    	arquivoModel.setCriado_em(arquivoModelOptional.get().getCriado_em());
    	arquivoModel.setConteudo(conteudoModelOptional.get());
    	
    	return ResponseEntity.status(HttpStatus.OK).body(arquivoService.save(arquivoModel));
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArquivo(@PathVariable(value = "id") Integer id){
    	Optional<ArquivoModel> arquivoModelOptional = arquivoService.findById(id);
    	if(!arquivoModelOptional.isPresent()) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found the object Arquivo");
    	}
    	arquivoService.delete(arquivoModelOptional.get());
    	return ResponseEntity.status(HttpStatus.OK).body("Conteudo deleted succesfully");
    }
}
