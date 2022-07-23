package com.api.alvaro.lembrina.serviceimplements;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.api.alvaro.lembrina.helpers.File;

public interface FileServiceAPI {
	
	public String save(MultipartFile file) throws Exception;
	
	public Resource load(String name) throws Exception;
	
	public List<File> save(List<MultipartFile> files) throws Exception;
	
	public Stream<Path> loadAll() throws Exception;
	
	public String getUrl(String filename) throws Exception;
	
	public String extrairExtensao(String nomeArquivo) throws Exception;
}
