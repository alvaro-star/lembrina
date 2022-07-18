package com.api.alvaro.lembrina.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.api.alvaro.lembrina.dtos.ArquivoDto;
import com.api.alvaro.lembrina.models.ArquivoModel;
import com.api.alvaro.lembrina.models.ConteudoModel;
import com.api.alvaro.lembrina.models.File;
import com.api.alvaro.lembrina.services.ArquivoService;
import com.api.alvaro.lembrina.services.ConteudoService;

@RestController
@RequestMapping("/arquivos")
@CrossOrigin(origins = "*", maxAge = 3600)
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
    
    
    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFiles(@RequestParam("files") List<MultipartFile> files){
    	try {
			arquivoService.saveAll(files);
			return ResponseEntity.status(HttpStatus.OK).body("Arquivos carregados corretamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao subir os arquivos");
		}
    }
    
	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
		Resource resource = arquivoService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<File>> getAllFiles() throws Exception {
		List<File> files = arquivoService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(ArquivoController.class, "getFile", path.getFileName().toString()).build().toString();
			
			return new File(filename, url);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
}
