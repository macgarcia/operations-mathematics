package com.macgarcia.operations.exception;

import java.io.Serializable;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorProcess implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
	
	private String message;
	
	private String dateProcess;
	
	private String process;

	public ErrorProcess(int code, String message, String process) {
		this.code = code;
		this.message = message;
		this.dateProcess = Instant.now().toString();
		this.process = process;
	}
}
