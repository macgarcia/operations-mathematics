package com.macgarcia.operations.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.macgarcia.operations.core.CoreCalculatorLogarithm;
import com.macgarcia.operations.dto.in.LogarithmDtoIn;

@RestController
@RequestMapping(path = "/logarithm")
public class LogarithmResource {

	@Autowired
	private CoreCalculatorLogarithm service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logarithm(@RequestParam final Integer log, @RequestParam final Integer base) {
		
		final LogarithmDtoIn dto = new LogarithmDtoIn(log, base);
		
		return this.service.calculator(dto);
	}
}
