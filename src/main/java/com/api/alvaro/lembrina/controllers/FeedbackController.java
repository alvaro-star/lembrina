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

import com.api.alvaro.lembrina.dtos.FeedbackDto;
import com.api.alvaro.lembrina.models.ConteudoModel;
import com.api.alvaro.lembrina.models.FeedbackModel;
import com.api.alvaro.lembrina.services.ConteudoService;
import com.api.alvaro.lembrina.services.FeedbackService;

@RestController
@RequestMapping("/feedBacks")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private ConteudoService conteudoService;
	
	@GetMapping
	public ResponseEntity<Page<FeedbackModel>> getAllArquivos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(feedbackService.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneArquivo(@PathVariable(value = "id") Integer id){
		Optional<FeedbackModel> feedbackModelOptional = feedbackService.findById(id);
		if(!feedbackModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Not Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(feedbackModelOptional.get());
	}
	
	@PostMapping
    public ResponseEntity<Object> saveArquivo(@RequestBody @Valid FeedbackDto feedbackDto){
		var feedbackModel = feedbackService.dtoToModel(feedbackDto);
		
		Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(feedbackDto.getIdConteudo());
		if(!conteudoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found the Object Conteudo");
		}
		feedbackModel.setConteudo(conteudoModelOptional.get());
		feedbackModel.setPostado_em(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.save(feedbackModel));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArquivo(@PathVariable(value = "id") Integer id, 
    												@RequestBody @Valid FeedbackDto feedbackDto){
    	Optional<FeedbackModel> feedbackModelOptional = feedbackService.findById(id);
		if(!feedbackModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Not Found");
		}
    	
		Optional<ConteudoModel> conteudoModelOptional = conteudoService.findById(feedbackDto.getIdConteudo());
		if(!conteudoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found the Object Conteudo");
		}
		
		var feedbackModel = feedbackService.dtoToModel(feedbackDto);
		feedbackModel.setId(feedbackModelOptional.get().getId());
    	feedbackModel.setConteudo(conteudoModelOptional.get());
		feedbackModel.setPostado_em(feedbackModelOptional.get().getPostado_em());
    	
		return ResponseEntity.status(HttpStatus.OK).body(feedbackService.save(feedbackModel));
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArquivo(@PathVariable(value = "id") Integer id){
    	Optional<FeedbackModel> feedbackModelOptional = feedbackService.findById(id);
		if(!feedbackModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object Not Found");
		}
    	feedbackService.delete(feedbackModelOptional.get());
    	return ResponseEntity.status(HttpStatus.OK).body("Conteudo deleted succesfully");
    }
}
