package com.api.alvaro.lembrina.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.api.alvaro.lembrina.controllers.FileController;
import com.api.alvaro.lembrina.helpers.File;
import com.api.alvaro.lembrina.serviceimplements.FileServiceAPI;

@Service
public class FileService implements FileServiceAPI {

	private final Path rootFolder = Paths.get("uploads");

	@Override
	public String save(MultipartFile file) throws Exception {
		String filename = UUID.randomUUID() + "." + extrairExtensao(file.getOriginalFilename());
		Files.copy(file.getInputStream(), this.rootFolder.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		return filename;
	}

	@Override
	public Resource load(String name) throws Exception {
		Path file = rootFolder.resolve(name);
		Resource resource = new UrlResource(file.toUri());
		return resource;
	}

	@Override
	public List<File> save(List<MultipartFile> files) throws Exception {
		List<File> arquivos = new ArrayList<File>();
		
		for (MultipartFile file : files) {
			File arquivo = new File();
			arquivo.setName(this.save(file));
			arquivos.add(arquivo);
		}
		
		return arquivos;
	}

	@Override
	public Stream<Path> loadAll() throws Exception {
		return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
	}
	
	@Override
	public String getUrl(String filename) throws Exception {
		String caminho = "uploads/"+filename;
		Path arquivo = Paths.get(caminho);
		String url = MvcUriComponentsBuilder.fromMethodName(FileController.class, "getFile", arquivo.getFileName().toString()).build().toString();
		return url;
	}
	
	@Override
    public String extrairExtensao(String nomeArquivo) throws Exception{
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }

}
