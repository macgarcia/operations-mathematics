package com.macgarcia.operations.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.macgarcia.operations.core.CoreCalculatorSecondDegreeEquation;
import com.macgarcia.operations.dto.in.SecondDegreeEquationDtoIn;

@RestController
@RequestMapping(path = "/secondDegreeEquation")
public class SecondDegreeEquationResource {
	
	@Autowired
	private CoreCalculatorSecondDegreeEquation service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bhaskara(@RequestParam final Integer a, @RequestParam final Integer b,
			@RequestParam final Integer c) {

		final SecondDegreeEquationDtoIn dto = new SecondDegreeEquationDtoIn(a, b, c);
		return this.service.calculator(dto);
	}
}
