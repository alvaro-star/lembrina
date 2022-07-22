package com.api.alvaro.lembrina.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.api.alvaro.lembrina.models.File;
import com.api.alvaro.lembrina.services.FileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/archivo")
public class UploadArquivoController {

	@Autowired
	private FileService fileService;
	
	@PostMapping("/arquivo")
	public ResponseEntity<Object> uploadArquivo(@RequestParam("file") MultipartFile file){
		//log.info("Recebendo Arquivo", file.getOriginalFilename());
		var path = "/home/teco/temp/arquivo";
		var caminho = path + UUID.randomUUID()+"."+extrairExtensao(file.getOriginalFilename());
		//log.info
		
		try {
			Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
			return ResponseEntity.status(HttpStatus.OK).body("O envio voi realizado com sucessor");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body("Erro de carregamento");
		}
	}
	
	public String extrairExtensao(String nomeArquivo) {
		int i = nomeArquivo.lastIndexOf(".");
		return nomeArquivo.substring(i+1);
	}

	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
		fileService.saveAll(files);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Los archivos fueron cargados correctamente al servidor");
	}

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
		Resource resource = fileService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<File>> getAllFiles() throws Exception {
		List<File> files = fileService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(UploadArquivoController.class, "getFile", path.getFileName().toString()).build().toString();
			
			return new File(filename, url);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
}
