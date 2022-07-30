package com.api.alvaro.lembrina.helpers;

import java.util.HashMap;

public class Response {
	
	private String message;
	HashMap<String, String> erros = new HashMap<>();

	public String getMessage() {
		return message;
	}

	public Response(String message) {
		super();
		this.message = message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HashMap<String, String> getErros() {
		return erros;
	}

	public void setErros(HashMap<String, String> erros) {
		this.erros = erros;
	}
}
