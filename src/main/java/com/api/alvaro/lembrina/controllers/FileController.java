package com.api.alvaro.lembrina.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.api.alvaro.lembrina.helpers.File;
import com.api.alvaro.lembrina.serviceimplements.FileServiceAPI;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileServiceAPI fileServiceAPI;

	@PostMapping("/upload")
	public ResponseEntity<List<File>> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
		List<File> arquivos = fileServiceAPI.save(files);
		
		for(File file:arquivos) {
			var url = fileServiceAPI.getUrl(file.getName());
			file.setUrl(url);
		}
		 
		return ResponseEntity.status(HttpStatus.OK).body(arquivos);
	}

	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
		Resource resource = fileServiceAPI.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<File>> getAllFiles() throws Exception {
		List<File> files = fileServiceAPI.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
			
			File file = new File();
			file.prencher(filename, url);
			return file;
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@GetMapping("{filename:.+}")
	public ResponseEntity<Object> getUrlOneFIle(@PathVariable String filename) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(fileServiceAPI.getUrl(filename));
	}

}
