package com.api.alvaro.lembrina.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.alvaro.lembrina.dtos.ArquivoDto;
import com.api.alvaro.lembrina.models.ArquivoModel;
import com.api.alvaro.lembrina.repositories.ArquivoRepository;

@Service
public class ArquivoService {

	@Autowired
	ArquivoRepository arquivoRepository;
	
	private final Path rootFolder = Paths.get("archives");
	
	public void save(MultipartFile file) throws Exception{
		Files.copy(file.getInputStream(), this.rootFolder.resolve(file.getOriginalFilename()));
	}
	
	public void saveAll(List<MultipartFile> files) throws Exception{
		for(MultipartFile file: files) {
			this.save(file);
		}
	}
	
	public Resource load(String name) throws Exception {
		Path file = rootFolder.resolve(name);
		Resource resource = new UrlResource(file.toUri());
		return resource;
	}
	
	public Stream<Path> loadAll() throws Exception {
		return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
	}
	
	@Transactional
	public ArquivoModel save(ArquivoModel arquivoModel) {
		return arquivoRepository.save(arquivoModel);
	}
	
	public Page<ArquivoModel> findAll(Pageable pageable) {
		return arquivoRepository.findAll(pageable);
	}

	public Optional<ArquivoModel> findById(Integer id) {
		return arquivoRepository.findById(id);
	}

	@Transactional
	public void delete(ArquivoModel arquivoModel) {
		arquivoRepository.delete(arquivoModel);
	}
	
	public ArquivoModel dtoToModel(ArquivoDto arquivoDto) {
		var arquivoModel = new ArquivoModel();
		arquivoModel.setNomeLocal(arquivoDto.getNomeLocal());
		arquivoModel.setUrl(arquivoDto.getUrl());
		arquivoModel.setNome(arquivoDto.getNome());
		return arquivoModel;
	}
	
	
}
