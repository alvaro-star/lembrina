package com.api.alvaro.lembrina.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/upload")
public class UploadAqrquivo {

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
}
