package com.macgarcia.operations.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.macgarcia.operations.core.CoreCalculatorHypotenuse;
import com.macgarcia.operations.dto.in.TriangleRectangle;

@RestController
@RequestMapping(path = "/hypotenuse")
public class HypotenuseResource {
	
	@Autowired
	private CoreCalculatorHypotenuse service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> hypotenuse(@RequestParam final Integer catOne, @RequestParam final Integer catTwo,
			@RequestParam final Integer hyp) {
		
		final TriangleRectangle dto = new TriangleRectangle(catOne, catTwo, hyp);
		
		return this.service.calculator(dto);
	}

}
