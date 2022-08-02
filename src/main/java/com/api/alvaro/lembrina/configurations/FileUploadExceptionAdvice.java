package com.api.alvaro.lembrina.configurations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handMaxSizeException(MaxUploadSizeExceededException ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verifique o tamanho dos arquivos");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handException(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verifique o tamanho dos arquivos");
	}
	

}
